package com.example.userservice.controller;
import com.example.userservice.Models.Bike;
import com.example.userservice.Models.Car;
import com.example.userservice.entity.Users;
import com.example.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<Users>> getAll (){
        List<Users> users = userService.getAll();
        if(users.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(users);
     }

     @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable(value = "id") int id){
        Users users = userService.getUserById(id);
        if(users == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(users);
    }

    @PostMapping()
    public ResponseEntity<Users> saveUser(@RequestBody Users users){
        Users userNew = userService.saveUser(users);
        return ResponseEntity.ok(userNew);
    }

    @GetMapping("/cars/{userId}")
    public ResponseEntity<List<Car>> getCars (@PathVariable("userId") int userId){
        Users user = userService.getUserById(userId);
        if(user == null)
            return ResponseEntity.noContent().build();
        List<Car> cars = userService.getCars (userId);
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/bikes/{userId}")
    public ResponseEntity<List<Bike>> getBikes (@PathVariable("userId") int userId){
        Users users = userService.getUserById(userId);
        if(users == null)
            return ResponseEntity.noContent().build();
        List<Bike> bikes = userService.getBike(userId);
        return ResponseEntity.ok(bikes);
    }

    @PostMapping("/savecar/{userId}")
    public ResponseEntity saveCar(@PathVariable("userId")int userId,@RequestBody Car car){
        if (userService.getUserById(userId)==null)
            return ResponseEntity.noContent().build();
        Car carNew = userService.saveCar(userId, car);
        return ResponseEntity.ok(carNew);
    }

    @PostMapping("/savebike/{userId}")
    public ResponseEntity saveBike(@PathVariable("userId")int userId,@RequestBody Bike bike){
        if (userService.getUserById(userId)==null)
            return ResponseEntity.noContent().build();
        Bike bikeNew = userService.saveBike(userId, bike);
        return ResponseEntity.ok(bikeNew);
    }

    @GetMapping("getAll/{userId}")
    public ResponseEntity<Map<String,Object>> getVehicles(@PathVariable("userId")int userId){
        Map<String,Object> result = userService.getUsersAndVehicles(userId);
        return ResponseEntity.ok(result);
    }
}
