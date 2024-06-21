package com.eventify.backend.controllers;

import com.eventify.backend.entities.PaymentHistoryEntity;
import com.eventify.backend.services.servicesInter.PaymentHistoryServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentHistoryController {
    @Autowired
    private PaymentHistoryServiceInter paymentHistoryService;

    @GetMapping
    public List<PaymentHistoryEntity> getAllPayments() {
        return paymentHistoryService.getAllPayments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentHistoryEntity> getPaymentById(@PathVariable Long id) {
        PaymentHistoryEntity payment = paymentHistoryService.getPaymentById(id);
        return payment != null ? ResponseEntity.ok(payment) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public PaymentHistoryEntity createPayment(@RequestBody PaymentHistoryEntity payment) {
        return paymentHistoryService.createPayment(payment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentHistoryEntity> updatePayment(@PathVariable Long id, @RequestBody PaymentHistoryEntity paymentDetails) {
        PaymentHistoryEntity updatedPayment = paymentHistoryService.updatePayment(id, paymentDetails);
        return updatedPayment != null ? ResponseEntity.ok(updatedPayment) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public void deletePayment(@PathVariable Long id) {
        paymentHistoryService.deletePayment(id);
    }
}
