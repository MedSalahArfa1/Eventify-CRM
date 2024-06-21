package com.eventify.backend.services.servicesImpl;

import com.eventify.backend.entities.FeedbackEntity;
import com.eventify.backend.repositories.FeedbackRepository;
import com.eventify.backend.services.servicesInter.FeedbackServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackServiceInter {
    @Autowired
    private FeedbackRepository feedbackRepository;

    @Override
    public List<FeedbackEntity> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    @Override
    public FeedbackEntity getFeedbackById(Long id) {
        return feedbackRepository.findById(id).orElse(null);
    }

    @Override
    public FeedbackEntity createFeedback(FeedbackEntity feedback) {
        return feedbackRepository.save(feedback);
    }

    @Override
    public FeedbackEntity updateFeedback(Long id, FeedbackEntity feedbackDetails) {
        FeedbackEntity feedback = feedbackRepository.findById(id).orElse(null);
        if (feedback != null) {
            feedback.setRating(feedbackDetails.getRating());
            feedback.setComments(feedbackDetails.getComments());
            feedback.setDateTime(feedbackDetails.getDateTime());
            feedback.setEvent(feedbackDetails.getEvent());
            feedback.setLeftBy(feedbackDetails.getLeftBy());
            return feedbackRepository.save(feedback);
        }
        return null;
    }

    @Override
    public void deleteFeedback(Long id) {
        feedbackRepository.deleteById(id);
    }
}
