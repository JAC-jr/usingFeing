package com.example.servicio2.controller;

import com.example.servicio2.entity.Bike;
import com.example.servicio2.service.BikeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/bikes")
public class BikeController {

    @Autowired
    private BikeService bikeService;

    @GetMapping
    public ResponseEntity<List<Bike>> getAll() {
        List<Bike> bikes = bikeService.getAll();
        if (bikes.isEmpty()) {
            log.info("no bikes in list");
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        log.info("showing list");
        return ResponseEntity.ok(bikes);
    }

    @PostMapping()
    public ResponseEntity<String> saveBike(@RequestBody Bike bike) {
        Bike bikeNew = bikeService.saveBike(bike);
        log.info("new bike {}", bike);
        return new ResponseEntity<>("new bike created", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bike> getBikeById(@PathVariable("id") int id) {
        Bike bike = bikeService.getBikeById(id);
        if (bike == null) {
            log.error("bike with id {} not found.", id);
            new ResourceNotFoundException("bike not exist with id: " + id);
        }
        log.info("bike whit id {} found.", id);
        return ResponseEntity.ok(bike);
    }

    @GetMapping("/byUserId/{userId}")
    public ResponseEntity<List<Bike>> getByUserId(@PathVariable("userId") int userId) {
        List<Bike> bike = bikeService.getByUserId(userId);
        if (bike.isEmpty()) {
            log.error("bikes from the user of id {} not found.", userId);
            new ResourceNotFoundException("bike not exist for user id: " + userId);
        }
        log.info("showing list of bikes for the user whit id {}", userId);
        return ResponseEntity.ok(bike);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBike(@PathVariable("id") int id) {
        Bike bike = bikeService.getBikeById(id);
        if (bike == null) {
            log.error("bike with id {} not found.", id);
            new ResourceNotFoundException("bike not exist with id: " + id);
        }
        bike = bikeService.deleteBike(id);
        log.info("bike with id {} deleted", id);
        return new ResponseEntity<>("bike deleted", HttpStatus.OK);
    }

    @PutMapping("/updateBike/{id}")
    public ResponseEntity<String> updateBike(@PathVariable("id")int id) {
        Bike bike = bikeService.getBikeById(id);
        if (bike==null){
            log.error("bike with id {} not found.", id);
            new ResourceNotFoundException("bike not exist with id: " + id);
        }
        Bike bikeNew = bikeService.saveBike(bike);
        log.info("updated bike whit id {}", id);
        return new ResponseEntity<>("bike updated", HttpStatus.OK);
    }
}
