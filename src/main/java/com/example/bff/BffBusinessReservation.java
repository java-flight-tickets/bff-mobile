package com.example.bff;

import com.example.bff.dto.ReservationDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.logging.Logger;

@Service
public class BffBusinessReservation {
    @Value("http://localhost:8080")
    private String reservationsServiceUrl;

    private static final Logger log = Logger.getLogger(BffBusinessReservation.class.toString());

    public void ReservationsService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity<String> getReservations() {
        return restTemplate.getForEntity(reservationsServiceUrl + "/reservations", String.class);
    }
}
