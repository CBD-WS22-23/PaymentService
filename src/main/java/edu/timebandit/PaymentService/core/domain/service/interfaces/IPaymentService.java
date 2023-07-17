package edu.timebandit.PaymentService.core.domain.service.interfaces;

import edu.timebandit.PaymentService.core.appservice.Payment;

public interface IPaymentService {

    String createPayment(String orderID, Double amount, String method);

    Payment getPaymentByID(String paymentID);

    Iterable<Payment> getAllPayments();

    void deletePayment(String paymentID);

    void processPayment(String paymentID);

    void cancelPayment(String paymentID);
}
