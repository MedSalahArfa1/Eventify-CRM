package com.eventify.backend.controllers;

import com.eventify.backend.entities.FeedbackEntity;
import com.eventify.backend.services.servicesInter.FeedbackServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedbacks")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FeedbackController {
    @Autowired
    private FeedbackServiceInter feedbackService;

    @GetMapping
    public List<FeedbackEntity> getAllFeedbacks() {
        return feedbackService.getAllFeedbacks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeedbackEntity> getFeedbackById(@PathVariable Long id) {
        FeedbackEntity feedback = feedbackService.getFeedbackById(id);
        return feedback != null ? ResponseEntity.ok(feedback) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public FeedbackEntity createFeedback(@RequestBody FeedbackEntity feedback) {
        return feedbackService.createFeedback(feedback);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeedbackEntity> updateFeedback(@PathVariable Long id, @RequestBody FeedbackEntity feedbackDetails) {
        FeedbackEntity updatedFeedback = feedbackService.updateFeedback(id, feedbackDetails);
        return updatedFeedback != null ? ResponseEntity.ok(updatedFeedback) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public void deleteFeedback(@PathVariable Long id) {
        feedbackService.deleteFeedback(id);
    }
}
