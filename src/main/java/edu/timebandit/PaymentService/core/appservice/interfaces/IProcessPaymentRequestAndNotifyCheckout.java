package edu.timebandit.PaymentService.core.appservice.interfaces;

import edu.timebandit.PaymentService.port.checkout.dtos.PaymentRequestDTO;

public interface IProcessPaymentRequestAndNotifyCheckout {
    void processAndNotify(PaymentRequestDTO paymentRequest);
}
