package com.aforo.project.controller;

import com.aforo.project.dto.ProducerDTO;
import com.aforo.project.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/producers")
public class ProducerController {

    @Autowired
    private ProducerService producerService;

    // Get All Producers
    @GetMapping
    public ResponseEntity<List<ProducerDTO>> getAllProducers() {
        List<ProducerDTO> producers = producerService.getAllProducers();
        return ResponseEntity.ok(producers);
    }

    // Get a Single Producer by ID
    @GetMapping("/{id}")
    public ResponseEntity<ProducerDTO> getProducerById(@PathVariable Long id) {
        Optional<ProducerDTO> producerDTO = Optional.of(producerService.getProducerById(id));
        return producerDTO
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new Producer
    @PostMapping
    public ResponseEntity<ProducerDTO> createProducer(@RequestBody ProducerDTO producerDTO) {
        ProducerDTO createdProducer = producerService.createProducer(producerDTO);
        return ResponseEntity.ok(createdProducer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProducerDTO> updateProducer(
        @PathVariable Long id,
        @RequestBody ProducerDTO producerDTO
    ) {
        ProducerDTO updatedProducer = producerService.updateProducer(id, producerDTO);
        return ResponseEntity.ok(updatedProducer);
    }


    // Delete a Producer by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<ProducerDTO> deleteProducer(@PathVariable Long id) {
        ProducerDTO deletedProducer = producerService.deleteProducer(id);
        return ResponseEntity.ok(deletedProducer);  // Return the deleted producer DTO
    }

}
