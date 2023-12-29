package com.example.basichttpserver.controller;

import com.example.basichttpserver.model.Greeting;
import com.example.basichttpserver.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ConcurrentMap;

/**
 * This class represents the Controller Layer.
 * It handles HTTP requests and responses.
 * It's the layer where different HTTP paths are mapped to Java methods.
 */
@RestController
public class GreetingController {

    @Autowired
    private GreetingService greetingService;

    @GetMapping("/greeting/{id}")
    public Greeting getGreeting(@PathVariable Long id) {
        return greetingService.getGreeting(id);
    }

    @PostMapping("/greeting")
    public Greeting createGreeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return greetingService.createGreeting(name);
    }

    @PutMapping("/greeting/{id}")
    public Greeting updateGreeting(@PathVariable Long id, @RequestParam String name) {
        return greetingService.updateGreeting(id, name);
    }

    @DeleteMapping("/greeting/{id}")
    public void deleteGreeting(@PathVariable Long id) {
        greetingService.deleteGreeting(id);
    }

    @GetMapping("/greetings")
    public ConcurrentMap<Long, Greeting> getAllGreetings() {
        return greetingService.getAllGreetings();
    }
}
