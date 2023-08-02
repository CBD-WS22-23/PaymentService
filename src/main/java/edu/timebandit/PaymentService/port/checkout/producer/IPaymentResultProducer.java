package edu.timebandit.PaymentService.port.checkout.producer;

import edu.timebandit.PaymentService.port.checkout.dtos.PaymentResultDTO;
import jakarta.validation.Valid;

public interface IPaymentResultProducer {
    void sendPaymentResultMessage(@Valid PaymentResultDTO paymentResult);
}
