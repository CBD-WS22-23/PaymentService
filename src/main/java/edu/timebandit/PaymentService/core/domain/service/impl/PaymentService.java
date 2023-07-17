package edu.timebandit.PaymentService.core.domain.service.impl;

import edu.timebandit.PaymentService.core.appservice.Payment;
import edu.timebandit.PaymentService.core.appservice.PaymentState;
import edu.timebandit.PaymentService.core.domain.service.interfaces.IPaymentRepository;
import edu.timebandit.PaymentService.core.domain.service.interfaces.IPaymentService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentService implements IPaymentService {

    private final IPaymentRepository paymentRepository;

    public PaymentService(IPaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public String createPayment(String orderID, Double amount, String method) {
        Payment payment = new Payment(UUID.randomUUID(),UUID.fromString(orderID), amount, method, PaymentState.PENDING);

        return paymentRepository.save(payment).getId().toString();
    }

    @Override
    public Payment getPaymentByID(String paymentID) {
        return paymentRepository.findById(UUID.fromString(paymentID)).orElse(null);
    }

    @Override
    public Iterable<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public void deletePayment(String paymentID) {
        paymentRepository.deleteById(UUID.fromString(paymentID));
    }

    @Override
    public void processPayment(String paymentID) {
        Payment payment = paymentRepository.findById(UUID.fromString(paymentID)).orElse(null);
        if (payment != null) {
            payment.setState(PaymentState.AUTHORIZED);
            paymentRepository.save(payment);
        }
    }

    @Override
    public void cancelPayment(String paymentID) {
        Payment payment = paymentRepository.findById(UUID.fromString(paymentID)).orElse(null);
        if (payment != null) {
            payment.setState(PaymentState.CANCELLED);
            paymentRepository.save(payment);
        }
    }


}
