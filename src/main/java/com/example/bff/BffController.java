package com.example.bff;

import com.example.bff.dto.ReservationDto;
import com.example.bff.dto.TicketDto;
import com.example.bff.dto.UserDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.logging.Logger;

@RestController
@CrossOrigin("*")
@RequestMapping("/")
public class BffController {

    private static final Logger log = Logger.getLogger(BffController.class.toString());

    private final BffBusinessUser businessUser;

    private final BffBusinessTicket businessTicket;

    private final BffBusinessReservation businessReservation;

    @Autowired
    public BffController(BffBusinessUser businessUser, BffBusinessTicket businessTicket, BffBusinessReservation businessReservation) {
        this.businessUser = businessUser;
        this.businessTicket = businessTicket;
        this.businessReservation = businessReservation;
    }

    @GetMapping("/users")
    public ResponseEntity<String> getUsers() {
        try {
            ResponseEntity<String> data = businessUser.getUsers();
            if (!data.getStatusCode().is2xxSuccessful()) {
                throw new ResponseStatusException(HttpStatus.valueOf(data.getStatusCodeValue()), "Error getting users");
            }

            return ResponseEntity
                    .ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(data.getBody());
        } catch (Exception e) {
            log.severe("Error getting users: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error handling request");
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<String> getUserById(@PathVariable String id) {
        try {
            ResponseEntity<String> data = businessUser.getUserById(id);
            if (!data.getStatusCode().is2xxSuccessful()) {
                throw new ResponseStatusException(HttpStatus.valueOf(data.getStatusCodeValue()), "Error getting user");
            }

            return ResponseEntity
                    .ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(data.getBody());
        } catch (Exception e) {
            log.severe("Error getting user: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error handling request");
        }
    }

    @GetMapping("/tickets")
    ResponseEntity<?> getAllTickets() {
        var data = businessTicket.getAllTickets();
        return ResponseEntity.ok(BffResponse.builder().data(data).build());
    }

    @GetMapping("/tickets/{id}")
    ResponseEntity<BffResponse<Object>> getTicketById(@PathVariable String id) {
        var data = businessTicket.getTicketById(id);
        return ResponseEntity.ok(BffResponse.builder().data(data).build());
    }

    @GetMapping("/reservations")
    public ResponseEntity<String> getReservations() {
        try {
            ResponseEntity<String> data = businessReservation.getReservations();
            if (!data.getStatusCode().is2xxSuccessful()) {
                throw new ResponseStatusException(HttpStatus.valueOf(data.getStatusCodeValue()), "Error getting reservations");
            }

            return ResponseEntity
                    .ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(data.getBody());
        } catch (Exception e) {
            log.severe("Error getting users: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error handling request");
        }
    }

}
