package com.eventify.backend.services.servicesImpl;

import com.eventify.backend.entities.FeedbackEntity;
import com.eventify.backend.repositories.FeedbackRepository;
import com.eventify.backend.services.servicesInter.FeedbackServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackServiceImpl implements FeedbackServiceInter {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Override
    public List<FeedbackEntity> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    @Override
    public Optional<FeedbackEntity> getFeedbackById(Long feedbackId) {
        return feedbackRepository.findById(feedbackId);
    }

    @Override
    public FeedbackEntity createFeedback(FeedbackEntity feedback) {
        return feedbackRepository.save(feedback);
    }

    @Override
    public FeedbackEntity updateFeedback(Long feedbackId, FeedbackEntity feedback) {
        Optional<FeedbackEntity> existingFeedback = feedbackRepository.findById(feedbackId);
        if (existingFeedback.isPresent()) {
            FeedbackEntity updatedFeedback = existingFeedback.get();
            updatedFeedback.setRating(feedback.getRating());
            updatedFeedback.setComments(feedback.getComments());
            updatedFeedback.setDateTime(feedback.getDateTime());
            updatedFeedback.setEvent(feedback.getEvent());
            updatedFeedback.setLeftBy(feedback.getLeftBy());
            return feedbackRepository.save(updatedFeedback);
        } else {
            throw new RuntimeException("Feedback not found with ID: " + feedbackId);
        }
    }

    @Override
    public void deleteFeedback(Long feedbackId) {
        feedbackRepository.deleteById(feedbackId);
    }
}
