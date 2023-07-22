package edu.timebandit.PaymentService.port.checkout.producer;

import edu.timebandit.PaymentService.port.checkout.dtos.PaymentResultDTO;

public interface IPaymentResultProducer {
    void sendPaymentResultMessage(PaymentResultDTO paymentResult);
}
