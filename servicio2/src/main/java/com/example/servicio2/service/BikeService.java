package com.example.servicio2.service;

import com.example.servicio2.entity.Bike;
import com.example.servicio2.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BikeService {

    @Autowired
    BikeRepository bikeRepository;

    public List<Bike> getAll() {
        return bikeRepository.findAll();
    }

    public Bike getBikeById(int id) {
        return bikeRepository.findById(id).orElse(null);
    }

    public Bike saveBike(Bike bike) {
        Bike bikeNew = bikeRepository.save(bike);
        return bikeNew;
    }

    public List<Bike> getByUserId(int userId) {
        return bikeRepository.findByUserId(userId);
    }

    public Bike deleteBike(int id) {
        Bike bike = bikeRepository.findById(id).orElse(null);
        bikeRepository.deleteById(id);
        return bike;
    }
}