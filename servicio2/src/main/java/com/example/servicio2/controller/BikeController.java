package com.example.servicio2.controller;

import com.example.servicio2.entity.Bike;
import com.example.servicio2.service.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bikes")
public class BikeController {

    @Autowired
    private BikeService bikeService;

    @GetMapping
    public ResponseEntity<List<Bike>> getAll (){
        List<Bike> bikes = bikeService.getAll();
        if(bikes.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(bikes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bike> getBikeById(@PathVariable(value = "id") int id){
        Bike bike = bikeService.getBikeById(id);
        if(bike == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(bike);
    }

    @PostMapping()
    public ResponseEntity<Bike> saveBike(@RequestBody Bike bike){
        Bike bikeNew = bikeService.saveBike(bike);
        return ResponseEntity.ok(bikeNew);
    }

    @GetMapping("/byUserId/{userId}")
    public ResponseEntity<List<Bike>> getByUserId(@PathVariable(value = "userId") int userId){
        List<Bike> bike = bikeService.getByUserId(userId);
        return ResponseEntity.ok(bike);
    }
}
