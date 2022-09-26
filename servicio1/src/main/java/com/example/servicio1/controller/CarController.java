package com.example.servicio1.controller;

import com.example.servicio1.entity.Car;
import com.example.servicio1.service.CarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping
    public ResponseEntity<List<Car>> getAll() {
        List<Car> cars = carService.getAll();
        if (cars.isEmpty()) {
            log.info("no cars in list");
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        log.info("showing list");
        return ResponseEntity.ok(cars);
    }

    @PostMapping()
    public ResponseEntity<String> saveCar(@RequestBody Car car) {
        Car carNew = carService.saveCar(car);
        log.info("new car {}", car);
        return new ResponseEntity<>("new car created", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable("id") int id) {
        Car car = carService.getCarById(id);
        if (car == null) {
            log.error("car with id {} not found.", id);
            new ResourceNotFoundException("Car not exist with id: " + id);
        }
        log.info("car whit id {} found.", id);
        return ResponseEntity.ok(car);
    }

    @GetMapping("/byUserId/{userId}")
    public ResponseEntity<List<Car>> getByUserId(@PathVariable("userId") int userId) {
        List<Car> car = carService.getByUserId(userId);
        if (car.isEmpty()) {
            log.error("cars from the user of id {} not found.", userId);
            new ResourceNotFoundException("Car not exist for user id: " + userId);
        }
        log.info("showing list of cars for the user whit id {}", userId);
        return ResponseEntity.ok(car);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCar(@PathVariable("id") int id) {
        Car car = carService.getCarById(id);
        if (car == null) {
            log.error("car with id {} not found.", id);
            new ResourceNotFoundException("Car not exist with id: " + id);
        }
        car = carService.deleteCar(id);
        log.info("car with id {} deleted", id);
        return new ResponseEntity<>("car deleted", HttpStatus.OK);

    }

    @PutMapping("/updateCar/{id}")
    public ResponseEntity<String> updateCar(@PathVariable("id")int id) {
        Car car = carService.getCarById(id);
        if (car==null){
            log.error("car with id {} not found.", id);
            new ResourceNotFoundException("Car not exist with id: " + id);
        }
        Car carNew = carService.saveCar(car);
        log.info("updated car whit id {}", id);
        return new ResponseEntity<>("car updated", HttpStatus.OK);
    }
}

