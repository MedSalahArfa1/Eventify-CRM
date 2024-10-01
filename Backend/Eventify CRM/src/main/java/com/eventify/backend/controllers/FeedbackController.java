package com.eventify.backend.controllers;

import com.eventify.backend.entities.FeedbackEntity;
import com.eventify.backend.services.servicesInter.FeedbackServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackServiceInter feedbackService;

    // Retrieve all feedbacks
    @GetMapping
    public List<FeedbackEntity> getAllFeedbacks() {
        return feedbackService.getAllFeedbacks();
    }

    // Retrieve a feedback by ID
    @GetMapping("/{id}")
    public ResponseEntity<FeedbackEntity> getFeedbackById(@PathVariable("id") Long feedbackId) {
        Optional<FeedbackEntity> feedback = feedbackService.getFeedbackById(feedbackId);
        if (feedback.isPresent()) {
            return ResponseEntity.ok(feedback.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Create a new feedback
    @PostMapping
    public FeedbackEntity createFeedback(@RequestBody FeedbackEntity feedback) {
        return feedbackService.createFeedback(feedback);
    }

    // Update a feedback by ID
    @PutMapping("/{id}")
    public ResponseEntity<FeedbackEntity> updateFeedback(@PathVariable("id") Long feedbackId, @RequestBody FeedbackEntity feedback) {
        try {
            FeedbackEntity updatedFeedback = feedbackService.updateFeedback(feedbackId, feedback);
            return ResponseEntity.ok(updatedFeedback);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a feedback by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable("id") Long feedbackId) {
        feedbackService.deleteFeedback(feedbackId);
        return ResponseEntity.noContent().build();
    }
}
