package com.eventify.backend.services.servicesInter;

import com.eventify.backend.entities.FeedbackEntity;

import java.util.List;
import java.util.Optional;

public interface FeedbackServiceInter {
    List<FeedbackEntity> getAllFeedbacks();
    Optional<FeedbackEntity> getFeedbackById(Long feedbackId);
    FeedbackEntity createFeedback(FeedbackEntity feedback);
    FeedbackEntity updateFeedback(Long feedbackId, FeedbackEntity feedback);
    void deleteFeedback(Long feedbackId);
}