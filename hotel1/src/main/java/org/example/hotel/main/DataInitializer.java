//package org.example.hotel.main;
//
//import org.example.hotel.model.*;
//import org.example.hotel.repository.*;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Arrays;
//
//@Component
//public class DataInitializer implements CommandLineRunner {
//
//    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);
//
//    private final AgenceRepository agenceRepository;
//    private final HotelRepository hotelRepository;
//    private final HotelAgenceRepository hotelAgenceRepository;
//    private final ChambreRepository chambreRepository;
//
//    public DataInitializer(AgenceRepository agenceRepository,
//                           HotelRepository hotelRepository,
//                           HotelAgenceRepository hotelAgenceRepository,
//                           ChambreRepository chambreRepository) {
//        this.agenceRepository = agenceRepository;
//        this.hotelRepository = hotelRepository;
//        this.hotelAgenceRepository = hotelAgenceRepository;
//        this.chambreRepository = chambreRepository;
//    }
//
//    @Override
//    public void run(String... args) {
//        logger.info("Initialisation des données...");
//
//
//        Agence agence1 = new Agence();
//        agence1.setEmail("test@agence.com");
//        agence1.setPassword("1234");
//        agence1.setNom("Agence Test");
//        agence1.setHotels(new java.util.ArrayList<>());
//        agenceRepository.save(agence1);
//        logger.info("Agence créée: {}", agence1.getNom());
//
//
//        Adresse adresse1 = new Adresse("France", "Paris", "Rue du Bonheur", "123", "48.8566,2.3522");
//        Hotel hotel1 = new Hotel();
//        hotel1.setNom("Hotel Paradis");
//        hotel1.setAdresse(adresse1);
//        hotel1.setEtoiles(4);
//        hotel1.setChambres(new java.util.ArrayList<>());
//        hotelRepository.save(hotel1);
//        logger.info("Hôtel créé: {}", hotel1.getNom());
//
//        Adresse adresse2 = new Adresse("France", "Paris", "Rue Oran", "310", "47.8566,2.3522");
//        Hotel hotel2 = new Hotel();
//        hotel2.setNom("Hotel Champs");
//        hotel2.setAdresse(adresse2);
//        hotel2.setEtoiles(5);
//        hotel2.setChambres(new java.util.ArrayList<>());
//        hotelRepository.save(hotel2);
//        logger.info("Hôtel créé: {}", hotel2.getNom());
//
//        createHotelAgence(hotel1, agence1, 0.15);
//        createHotelAgence(hotel2, agence1, 0.10);
//        logger.info("Liaisons hôtels-agences créées");
//
//
//        Chambre c1 = createChambre(hotel1, "Deluxe", 2, 150, true, "deluxe.jpg");
//        Chambre c2 = createChambre(hotel1, "Standard", 2, 100, true, "standard.jpg");
//        Chambre c3 = createChambre(hotel1, "Suite", 2, 250, true, "suite.jpg");
//        Chambre c4 = createChambre(hotel1, "Double", 2, 300, true, "superior.jpg");
//        chambreRepository.saveAll(Arrays.asList(c1, c2, c3, c4));
//        logger.info("Chambres hôtel1 ajoutées: {}, {}, {}, {}", c1.getType(), c2.getType(), c3.getType(), c4.getType());
//
//
//        Chambre c5 = createChambre(hotel2, "Standard", 2, 200, true, "champs.jpg");
//        Chambre c6 = createChambre(hotel2, "Suite", 3, 350, true, "champs2.jpg");
//        chambreRepository.saveAll(Arrays.asList(c5, c6));
//        logger.info("Chambres hôtel2 ajoutées: {}, {}", c5.getType(), c6.getType());
//
//        logger.info("Initialisation complète ✅");
//    }
//
//
//    private void createHotelAgence(Hotel hotel, Agence agence, double reduction) {
//        HotelAgence ha = new HotelAgence();
//        ha.setHotel(hotel);
//        ha.setAgence(agence);
//        ha.setReduction(reduction);
//        hotelAgenceRepository.save(ha);
//
//        if (agence.getHotels() == null) {
//            agence.setHotels(new java.util.ArrayList<>());
//        }
//        agence.getHotels().add(ha);
//        agenceRepository.save(agence);
//    }
//
//
//    private Chambre createChambre(Hotel hotel, String type, int lits, double prix, boolean dispo, String imageName) {
//        Chambre chambre = new Chambre();
//        chambre.setHotel(hotel);
//        chambre.setType(type);
//        chambre.setNombreLits(lits);
//        chambre.setPrix(prix);
//        chambre.setDisponible(dispo);
//        chambre.setImage(loadImageFromResources(imageName));
//
//        if (hotel.getChambres() == null) {
//            hotel.setChambres(new java.util.ArrayList<>());
//        }
//        hotel.getChambres().add(chambre);
//
//        return chambre;
//    }
//
//    private byte[] loadImageFromResources(String filename) {
//        try (InputStream is = getClass().getResourceAsStream("/images/" + filename)) {
//            if (is != null) {
//                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
//                int nRead;
//                byte[] data = new byte[16384];
//                while ((nRead = is.read(data, 0, data.length)) != -1) {
//                    buffer.write(data, 0, nRead);
//                }
//                buffer.flush();
//                return buffer.toByteArray();
//            } else {
//                logger.warn("Image {} introuvable !", filename);
//            }
//        } catch (IOException e) {
//            logger.error("Erreur lors du chargement de l'image {}", filename, e);
//        }
//        return new byte[0];
//    }
//}
