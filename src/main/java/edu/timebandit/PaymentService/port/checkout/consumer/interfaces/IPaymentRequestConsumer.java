package edu.timebandit.PaymentService.port.checkout.consumer.interfaces;

import edu.timebandit.PaymentService.port.checkout.dtos.PaymentRequestDTO;

public interface IPaymentRequestConsumer {
    void receivePaymentRequestMessage(PaymentRequestDTO paymentRequest);
}
