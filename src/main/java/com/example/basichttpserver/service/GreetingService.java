package com.example.basichttpserver.service;

import com.example.basichttpserver.model.Greeting;
import org.springframework.stereotype.Service;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Service Layer:
 * Contains business logic. It's where operations on data are defined and executed.
 */
@Service
public class GreetingService {

    private final AtomicLong counter = new AtomicLong();
    private final ConcurrentMap<Long, Greeting> greetings = new ConcurrentHashMap<>();

    public Greeting createGreeting(String name) {
        long id = counter.incrementAndGet();
        Greeting greeting = new Greeting(id, "Hello, " + name + "!");
        greetings.put(id, greeting);
        return greeting;
    }

    public Greeting createGreeting(String name, String msg) {
        long id = counter.incrementAndGet();
        Greeting greeting = new Greeting(id, "Hello, " + name + "!" + " Your msg :" + msg);
        greetings.put(id, greeting);
        return greeting;
    }

    public Greeting getGreeting(Long id) {
        return greetings.get(id);
    }

    public Greeting updateGreeting(Long id, String name) {
        Greeting greeting = new Greeting(id, "Hello, " + name + "!");
        greetings.put(id, greeting);
        return greeting;
    }

    public void deleteGreeting(Long id) {
        greetings.remove(id);
    }

    public ConcurrentMap<Long, Greeting> getAllGreetings() {
        return greetings;
    }
}
