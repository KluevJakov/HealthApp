package com.healthapp.healthapp.controller;

import com.healthapp.healthapp.repository.SymptomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8080"})
@RestController
@RequestMapping("/api/symptoms")
public class SymptomController {

    @Autowired
    private SymptomRepository symptomRepository;

    @GetMapping
    public ResponseEntity<?> getSymptoms() {
        return ResponseEntity.ok(symptomRepository.findAll());
    }
}
