package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class UrlShortenerControlle {

    private final Map<String, String> urlDatabase = new HashMap<>();
    // Endpunkt 1: Lange URL annehmen und kurzen String zurückgeben

    @PostMapping("/shorten")
    public String shortenUrl(@RequestBody String longUrl){
        String shortId = UUID.randomUUID().toString().substring(0, 6);
        urlDatabase.put(shortId, longUrl);
        return "http://localhost:8080/" + shortId;
    }

    // Endpunkt 2: Kurze ID annehmen und zur langen URL weiterleiten

    @GetMapping("/{id}")
    public ResponseEntity<Void> redirectUrl(@PathVariable String id){
        String longUrl = urlDatabase.get(id);

        if (longUrl != null){
            return ResponseEntity.status(HttpStatus.FOUND)
                    .location(URI.create(longUrl))
                    .build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }



}
