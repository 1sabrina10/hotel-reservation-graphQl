package org.example.comparateur.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.comparateur.model.Agence;
import org.example.comparateur.model.OffreAgence;
import org.example.comparateur.repository.AgenceRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.*;

@Service
public class ComparateurService {

    private final AgenceRepository agenceRepository;
    private final ObjectMapper mapper = new ObjectMapper();

    public ComparateurService(AgenceRepository agenceRepository, WebClient.Builder builder) {
        this.agenceRepository = agenceRepository;
    }

    public List<OffreAgence> getOffres(String ville, int nbPersonnes, Date dateDebut, Date dateFin) {

        List<OffreAgence> result = new ArrayList<>();

        List<Agence> agences = agenceRepository.findAll();
        
        ExchangeStrategies strategies = ExchangeStrategies.builder()
                .codecs(c -> c.defaultCodecs().maxInMemorySize(20 * 1024 * 1024))
                .build();
        
        for (Agence agence : agences) {
            Long agenceId = agence.getId();
            int port = 8080 + agenceId.intValue();
            String baseUrl = "http://localhost:" + port + "/graphql";
            
            WebClient client = WebClient.builder()
                    .baseUrl(baseUrl)
                    .exchangeStrategies(strategies)
                    .build();

            List<OffreAgence> offres = getOffresAgence(client, agenceId.intValue(), agence.getEmail(), agence.getPassword(), ville, nbPersonnes, dateDebut, dateFin);
            result.addAll(offres);
        }

        return result;
    }

    private List<OffreAgence> getOffresAgence(WebClient client, int agenceNum, String email, String password,
                                              String ville, int nbPersonnes, Date dateDebut, Date dateFin) {

        try {
            String query = """
                    query($email: String!, $password: String!, $nbPersonnes: Int!, $ville: String!, $dateDebut: Float!, $dateFin: Float!) {
                                      offres(email: $email, password: $password, nbPersonnes: $nbPersonnes, ville: $ville, dateDebut: $dateDebut, dateFin: $dateFin) {
                                          offreId
                                          agenceId
                                          chambreDTO {
                                              id
                                              nomHotel
                                              adresse {
                                                  pays
                                                  ville
                                                  rue
                                                  numero
                                                  gps
                                              }
                                              type
                                              nombreLits
                                              prix
                                              reduction
                                              image
                                              hasOffer
                                              etoiles
                                              dateDebut
                                              dateFin
                                              nombreNuits
                                              prixTotal
                                          }
                                      }
                                  }
            
            """;

            Map<String, Object> variables = new HashMap<>();
            variables.put("email", email);
            variables.put("password", password);
            variables.put("nbPersonnes", nbPersonnes);
            variables.put("ville", ville);
            variables.put("dateDebut", (float) dateDebut.getTime());
            variables.put("dateFin", (float) dateFin.getTime());

            Map<String, Object> body = new HashMap<>();
            body.put("query", query);
            body.put("variables", variables);

            System.out.println("Envoi requête GraphQL à l'agence " + agenceNum);

            JsonNode response = client.post()
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(body)
                    .retrieve()
                    .onStatus(status -> !status.is2xxSuccessful(), response2 -> {
                        System.err.println("Erreur HTTP " + response2.statusCode() + " de l'agence " + agenceNum);
                        return Mono.error(new RuntimeException("Erreur HTTP: " + response2.statusCode()));
                    })
                    .bodyToMono(JsonNode.class)
                    .block();

            if (response == null) {
                System.err.println("Réponse null de l'agence " + agenceNum);
                return Collections.emptyList();
            }

            if (response.has("errors")) {
                return Collections.emptyList();
            }

            JsonNode dataNode = response.get("data");
            if (dataNode == null) {
                return Collections.emptyList();
            }

            JsonNode offresNode = dataNode.get("offres");
            if (offresNode == null || !offresNode.isArray()) {
                return Collections.emptyList();
            }

            List<OffreAgence> offres = new ArrayList<>();
            java.util.Set<String> seenOffers = new java.util.HashSet<>();
            
            for (JsonNode node : offresNode) {
                try {
                    OffreAgence offre = mapper.treeToValue(node, OffreAgence.class);

                    String uniqueKey = offre.getAgenceId() + "_" + offre.getChambreDTO().getId();
                    if (!seenOffers.contains(uniqueKey)) {
                        seenOffers.add(uniqueKey);
                        offres.add(offre);
                    } else {
                        System.out.println("Offre dupliquée ignorée: " + uniqueKey);
                    }
                } catch (Exception e) {
                    System.err.println("Erreur lors de la conversion d'une offre de l'agence " + agenceNum + ": " + e.getMessage());
                    e.printStackTrace();
                }
            }

            System.out.println("Offres converties de l'agence " + agenceNum + ": " + offres.size());
            return offres;

        } catch (Exception e) {
            System.err.println("Exception lors de la récupération des offres de l'agence " + agenceNum + ": " + e.getMessage());
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
