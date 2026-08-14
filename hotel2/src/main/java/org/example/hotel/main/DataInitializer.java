/*
package org.example.hotel.main;

import org.example.hotel.model.Chambre;
import org.example.hotel.model.Hotel;
import org.example.hotel.repository.ChambreRepository;
import org.example.hotel.repository.HotelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
public class DataInitializer implements CommandLineRunner {


    private final HotelRepository hotelRepository;
    private final ChambreRepository chambreRepository;

    public DataInitializer(HotelRepository hotelRepository,
                                    ChambreRepository chambreRepository) {
        this.hotelRepository = hotelRepository;
        this.chambreRepository = chambreRepository;
    }

    @Override
    public void run(String... args) {

        // 1️⃣ Récupérer l'hôtel existant par son ID (ou autre critère)
        Hotel hotel = hotelRepository.findById(2L).orElse(null); // change l'ID selon ton hôtel
        if (hotel == null) {
            return;
        }

        // 2️⃣ Créer la chambre
        Chambre chambre = new Chambre();
        chambre.setHotel(hotel);
        chambre.setType("Suite");
        chambre.setNombreLits(2);
        chambre.setPrix(500);
        chambre.setDisponible(true);
        chambre.setImage(loadImageFromResources("suite.jpg"));

        chambreRepository.save(chambre);

    }

    private byte[] loadImageFromResources(String filename) {
        try (InputStream is = getClass().getResourceAsStream("/images/" + filename)) {
            if (is != null) {
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                int nRead;
                byte[] data = new byte[16384];
                while ((nRead = is.read(data, 0, data.length)) != -1) {
                    buffer.write(data, 0, nRead);
                }
                buffer.flush();
                return buffer.toByteArray();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }
}*/
