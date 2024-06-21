package com.eventify.backend.services.servicesInter;

import com.eventify.backend.entities.PaymentHistoryEntity;

import java.util.List;

public interface PaymentHistoryServiceInter {
    List<PaymentHistoryEntity> getAllPayments();

    PaymentHistoryEntity getPaymentById(Long id);

    PaymentHistoryEntity createPayment(PaymentHistoryEntity payment);

    PaymentHistoryEntity updatePayment(Long id, PaymentHistoryEntity paymentDetails);

    void deletePayment(Long id);
}
