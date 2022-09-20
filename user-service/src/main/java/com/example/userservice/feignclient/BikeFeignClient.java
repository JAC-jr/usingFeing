package com.example.userservice.feignclient;

import com.example.userservice.Models.Bike;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "service2", path = "/bikes", url = "http://localhost:8020")
public interface BikeFeignClient {

    @PostMapping
    Bike save (@RequestBody Bike bike);

    @GetMapping("/byUserId/{userId}")
    List<Bike> getBikes(@PathVariable("userId")int userId);

}


