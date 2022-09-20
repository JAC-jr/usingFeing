package com.example.userservice.feignclient;

import com.example.userservice.Models.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "service1",path = "/cars", url = "http://localhost:8011")
public interface CarFeignClient {

    @PostMapping
    Car save(@RequestBody Car car);

   @GetMapping("/byUserId/{userId}")
    List<Car> getCars(@PathVariable("userId")int userId);

}
