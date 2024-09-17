package com.aforo.project.controller;

import com.aforo.project.dto.SubscriptionDTO;
import com.aforo.project.exception.ResourceNotFoundException;
import com.aforo.project.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    // Get All Subscriptions
    @GetMapping
    public ResponseEntity<List<SubscriptionDTO>> getAllSubscriptions() {
        List<SubscriptionDTO> subscriptions = subscriptionService.getAllSubscriptions();
        return ResponseEntity.ok(subscriptions);
    }

    // Get a Single Subscription by ID
    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionDTO> getSubscriptionById(@PathVariable Long id) {
        SubscriptionDTO subscriptionDTO = subscriptionService.getSubscriptionById(id);
        return ResponseEntity.ok(subscriptionDTO);
    }

    // Create a new Subscription
    @PostMapping
    public ResponseEntity<SubscriptionDTO> createSubscription(@RequestBody SubscriptionDTO subscriptionDTO) {
        SubscriptionDTO createdSubscription = subscriptionService.createSubscription(subscriptionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSubscription);
    }

    // Update an existing Subscription
    @PutMapping("/{id}")
    public ResponseEntity<SubscriptionDTO> updateSubscription(@PathVariable Long id, @RequestBody SubscriptionDTO subscriptionDTO) {
        SubscriptionDTO updatedSubscription = subscriptionService.updateSubscription(id, subscriptionDTO);
        return ResponseEntity.ok(updatedSubscription);
    }

    // Delete a Subscription by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSubscription(@PathVariable Long id) {
        try {
            subscriptionService.deleteSubscription(id);
            return ResponseEntity.ok("Subscription Deleted Successfully");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Subscription not found with id: " + id);
        }
    }
}
  


