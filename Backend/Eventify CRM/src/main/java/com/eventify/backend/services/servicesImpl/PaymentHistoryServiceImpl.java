package com.eventify.backend.services.servicesImpl;

import com.eventify.backend.entities.PaymentHistoryEntity;
import com.eventify.backend.repositories.PaymentHistoryRepository;
import com.eventify.backend.services.servicesInter.PaymentHistoryServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentHistoryServiceImpl implements PaymentHistoryServiceInter {

    @Autowired
    private PaymentHistoryRepository paymentHistoryRepository;

    @Override
    public List<PaymentHistoryEntity> getAllPayments() {
        return paymentHistoryRepository.findAll();
    }

    @Override
    public PaymentHistoryEntity getPaymentById(Long id) {
        return paymentHistoryRepository.findById(id).orElse(null);
    }

    @Override
    public PaymentHistoryEntity createPayment(PaymentHistoryEntity payment) {
        return paymentHistoryRepository.save(payment);
    }

    @Override
    public PaymentHistoryEntity updatePayment(Long id, PaymentHistoryEntity paymentDetails) {
        PaymentHistoryEntity payment = paymentHistoryRepository.findById(id).orElse(null);
        if (payment != null) {
            payment.setAmount(paymentDetails.getAmount());
            payment.setDate(paymentDetails.getDate());
            payment.setStatus(paymentDetails.getStatus());
            payment.setUser(paymentDetails.getUser());
            payment.setEvent(paymentDetails.getEvent());
            return paymentHistoryRepository.save(payment);
        }
        return null;
    }

    @Override
    public void deletePayment(Long id) {
        paymentHistoryRepository.deleteById(id);
    }
}
