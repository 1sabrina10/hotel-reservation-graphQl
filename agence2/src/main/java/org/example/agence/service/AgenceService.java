package org.example.agence.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.agence.model.*;
import org.example.agence.repository.*;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.*;

@Service
public class AgenceService {

    private final WebClient webClient;
    private final WebClient webClient2;
    private final ObjectMapper mapper = new ObjectMapper();

    private final AgenceRepository agenceRepository;
    private final ClientRepository clientRepository;
    private final ReservationRepository reservationRepository;
    private final ChambreRepository chambreRepository;
    private final HotelRepository hotelRepository;
    private final HotelAgenceRepository hotelAgenceRepository;

    private final List<OffreAgence> offresMemoire = new ArrayList<>();

    public AgenceService(WebClient.Builder webClientBuilder,
                         AgenceRepository agenceRepository,
                         ClientRepository clientRepository,
                         ReservationRepository reservationRepository,
                         ChambreRepository chambreRepository,
                         HotelRepository hotelRepository,
                         HotelAgenceRepository hotelAgenceRepository) {

        this.agenceRepository = agenceRepository;
        this.clientRepository = clientRepository;
        this.reservationRepository = reservationRepository;
        this.chambreRepository = chambreRepository;
        this.hotelRepository = hotelRepository;
        this.hotelAgenceRepository = hotelAgenceRepository;

        ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs(c -> c.defaultCodecs().maxInMemorySize(10 * 1024 * 1024))
                .build();

        this.webClient = WebClient.builder()
                .baseUrl("http://localhost:8080/graphql")
                .exchangeStrategies(strategies)
                .build();

        this.webClient2 = WebClient.builder()
                .baseUrl("http://localhost:9090/graphql")
                .exchangeStrategies(strategies)
                .build();
    }

    public boolean authentifierAgence(String email, String password) {
        try {
            String query = """
                query($email: String!, $password: String!) {
                  auth(email: $email, password: $password)
                }
            """;

            Map<String, Object> body = Map.of(
                    "query", query,
                    "variables", Map.of("email", email, "password", password)
            );

            Mono<JsonNode> res = webClient.post()
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(body)
                    .retrieve()
                    .bodyToMono(JsonNode.class);

            JsonNode json = res.block();

            return json != null
                    && json.get("data") != null
                    && json.get("data").get("auth").asBoolean();

        } catch (Exception e) {
            return false;
        }
    }

    public List<OffreAgence> consulterOffres(String email, String password,
                                             int nbPersonnes, String ville,
                                             Date dateDebut, Date dateFin, Long agenceId) {

        Agence agence = agenceRepository
                .findFirstByEmailIgnoreCaseAndPassword(email, password)
                .orElse(null);

        if (agence == null) return List.of();

        Long finalAgenceId = (agenceId != null) ? agenceId : agence.getId();

        List<HotelAgence> hotelAgences = hotelAgenceRepository.findByAgenceId(finalAgenceId);

        List<ChambreDTO> chambres = new ArrayList<>();

        for (HotelAgence ha : hotelAgences) {
            Long hotelId = ha.getHotelId();
            WebClient clientToUse = (hotelId == 1L) ? webClient : webClient2;
            List<ChambreDTO> chambresHotel = getChambreDispo(clientToUse, hotelId, email, password, nbPersonnes, ville, dateDebut, dateFin);
            System.out.println("[AGENCE " + finalAgenceId + "] Chambres trouvées pour hotel " + hotelId + ": " + chambresHotel.size());
            chambres.addAll(chambresHotel);
        }

        offresMemoire.clear();

        for (ChambreDTO c : chambres) {
            hotelRepository.findFirstByNom(c.getNomHotel()).ifPresent(hotel -> {
                HotelAgence ha = hotelAgenceRepository.findByHotelIdAndAgenceId(hotel.getId(), finalAgenceId);
                if (ha == null) {
                    System.out.println("[AGENCE " + finalAgenceId + "] Pas de relation trouvée pour hotel " + hotel.getId());
                    return;
                }

                c.setDateDebut(dateDebut);
                c.setDateFin(dateFin);

                long diff = dateFin.getTime() - dateDebut.getTime();
                int nuits = Math.max(1, (int) (diff / (1000 * 60 * 60 * 24)));
                c.setNombreNuits(nuits);

                double prixFinal = c.getPrix() * (1 - ha.getReduction());
                c.setPrix(prixFinal);
                c.setReduction(ha.getReduction());
                c.setPrixTotal(prixFinal * nuits);

                OffreAgence o = new OffreAgence();
                o.setOffreId(System.nanoTime());
                o.setAgenceId(finalAgenceId);
                o.setChambreDTO(c);

                offresMemoire.add(o);
            });
        }

        System.out.println("[AGENCE " + finalAgenceId + "] Total offres créées: " + offresMemoire.size());
        return offresMemoire;
    }

    public OffreAgence getOffreById(Long agenceId, Long offreId) {
        return offresMemoire.stream()
                .filter(o -> o.getAgenceId().equals(agenceId) && o.getOffreId().equals(offreId))
                .findFirst()
                .orElse(null);
    }


    private List<ChambreDTO> getChambreDispo(WebClient client, Long hotelId,
                                             String email, String password, int nbPersonnes,
                                             String ville, Date dateDebut, Date dateFin) {
        try {
            String query = """
            query($hotelId: Long!, $email: String!, $password: String!,
                  $nbPersonnes: Int!, $ville: String!, $dateDebut: Long!, $dateFin: Long!) {
              disponibilites(hotelId: $hotelId, email: $email, password: $password,
                             nbPersonnes: $nbPersonnes, ville: $ville,
                             dateDebut: $dateDebut, dateFin: $dateFin) {
                id
                nomHotel
                adresse { pays ville rue numero gps }
                type
                nombreLits
                prix
                image
              }
            }
        """;

            Map<String, Object> body = Map.of(
                    "query", query,
                    "variables", Map.of(
                            "hotelId", hotelId,
                            "email", email,
                            "password", password,
                            "nbPersonnes", nbPersonnes,
                            "ville", ville,
                            "dateDebut", dateDebut.getTime(),
                            "dateFin", dateFin.getTime()
                    )
            );

            System.out.println("[DEBUG] Envoi requête GraphQL au hotel " + hotelId);
            System.out.println("[DEBUG] Variables: hotelId=" + hotelId + ", email=" + email + ", ville=" + ville + 
                             ", dateDebut=" + dateDebut.getTime() + ", dateFin=" + dateFin.getTime());

            JsonNode res = client.post()
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(body)
                    .retrieve()
                    .onStatus(status -> !status.is2xxSuccessful(), response -> {
                        System.err.println("[ERROR] Erreur HTTP " + response.statusCode() + " du hotel " + hotelId);
                        return Mono.error(new RuntimeException("Erreur HTTP: " + response.statusCode()));
                    })
                    .bodyToMono(JsonNode.class)
                    .block();

            if (res == null) {
                System.err.println("[ERROR] Réponse null du hotel " + hotelId);
                return List.of();
            }

            System.out.println("[DEBUG] Réponse reçue du hotel " + hotelId + ": " + res.toString());

            if (res.has("errors")) {
                System.err.println("[ERROR] Erreurs GraphQL du hotel " + hotelId + ": " + res.get("errors").toString());
                return List.of();
            }

            JsonNode dataNode = res.get("data");
            if (dataNode == null) {
                System.err.println("[ERROR] Pas de 'data' dans la réponse du hotel " + hotelId);
                return List.of();
            }

            JsonNode arr = dataNode.get("disponibilites");
            if (arr == null || !arr.isArray()) {
                System.err.println("[ERROR] Pas de 'disponibilites' ou ce n'est pas un tableau dans la réponse du hotel " + hotelId);
                return List.of();
            }

            System.out.println("[DEBUG] Nombre de chambres trouvées: " + arr.size());

            List<ChambreDTO> out = new ArrayList<>();
            for (JsonNode node : arr) {
                try {
                    ChambreDTO dto = mapper.treeToValue(node, ChambreDTO.class);
                    out.add(dto);
                } catch (Exception e) {
                    System.err.println("[ERROR] Erreur lors de la conversion d'une chambre: " + e.getMessage());
                    e.printStackTrace();
                }
            }
            System.out.println("[DEBUG] Chambres converties: " + out.size());
            return out;

        } catch (Exception e) {
            System.err.println("[ERROR] Exception lors de la récupération des chambres du hotel " + hotelId + ": " + e.getMessage());
            e.printStackTrace();
            return List.of();
        }
    }

    public Reservation reserverChambre(ReservationRequest r) {
        Date d1 = r.getDateDebut();
        Date d2 = r.getDateFin();
        if (!d2.after(d1)) return null;

        int nuits = Math.max(1,
                (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24)));
        r.setNombreNuits(nuits);

        Client client = clientRepository.findByEmail(r.getEmail()).stream()
                .findFirst()
                .orElseGet(() -> clientRepository.save(
                        new Client(r.getNomClient(), r.getPrenomClient(),
                                r.getEmail(), r.getNumeroCarte(), r.getCvc())
                ));

        Chambre chambre = chambreRepository.findById(r.getChambreId()).orElseThrow();

        HotelAgence ha = hotelAgenceRepository.findByHotelIdAndAgenceId(chambre.getHotel().getId(), r.getAgenceId());
        double reduc = ha != null ? ha.getReduction() : 0;
        double prixFinal = chambre.getPrix() * (1 - reduc);
        double total = prixFinal * nuits;

        Reservation res = new Reservation();
        res.setClient(client);
        res.setChambre(chambre);
        res.setAgenceId(r.getAgenceId());
        res.setPrixTotal(total);
        res.setReference("RES-" + System.currentTimeMillis());
        res.setNombreNuits(nuits);
        res.setDateDebut(d1);
        res.setDateFin(d2);

        return reservationRepository.save(res);
    }

    public boolean annulerReservation(String ref) {
        return reservationRepository.findByReference(ref)
                .map(r -> { reservationRepository.delete(r); return true; })
                .orElse(false);
    }

    public Reservation getReservationByReference(String ref) {
        return reservationRepository.findByReference(ref).orElse(null);
    }
}
