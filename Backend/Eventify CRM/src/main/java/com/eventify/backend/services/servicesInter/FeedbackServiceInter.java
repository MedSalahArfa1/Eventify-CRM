package com.eventify.backend.services.servicesInter;

import com.eventify.backend.entities.FeedbackEntity;

import java.util.List;

public interface FeedbackServiceInter {
    List<FeedbackEntity> getAllFeedbacks();

    FeedbackEntity getFeedbackById(Long id);

    FeedbackEntity createFeedback(FeedbackEntity feedback);

    FeedbackEntity updateFeedback(Long id, FeedbackEntity feedbackDetails);

    void deleteFeedback(Long id);
}
