package edu.timebandit.PaymentService.port.checkout.consumer.interfaces;

import edu.timebandit.PaymentService.port.checkout.dtos.PaymentRequestDTO;
import jakarta.validation.Valid;

public interface IPaymentRequestConsumer {
    void receivePaymentRequestMessage(@Valid PaymentRequestDTO paymentRequest);
}
