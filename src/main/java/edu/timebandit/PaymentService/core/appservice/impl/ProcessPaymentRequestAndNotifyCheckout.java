package edu.timebandit.PaymentService.core.appservice.impl;

import edu.timebandit.PaymentService.core.appservice.interfaces.IProcessPaymentRequestAndNotifyCheckout;
import edu.timebandit.PaymentService.core.domain.service.interfaces.IPaymentService;
import edu.timebandit.PaymentService.port.checkout.dtos.PaymentRequestDTO;
import edu.timebandit.PaymentService.port.checkout.dtos.PaymentResultDTO;
import edu.timebandit.PaymentService.port.checkout.producer.IPaymentResultProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ProcessPaymentRequestAndNotifyCheckout implements IProcessPaymentRequestAndNotifyCheckout {

    @Autowired
    private IPaymentService paymentService;

    @Autowired
    private IPaymentResultProducer paymentResultProducer;

    private final Random randomizer = new Random(System.currentTimeMillis());

    public void processAndNotify(PaymentRequestDTO paymentRequest){
        String createdPaymentID = paymentService.createPayment(
                paymentRequest.getOrderID(),
                paymentRequest.getTotalPrice(),
                paymentRequest.getPaymentMethod());

        boolean paymentSuccessful = randomizer.nextBoolean();

        if (paymentSuccessful) {
            paymentService.processPayment(createdPaymentID);
            paymentResultProducer.sendPaymentResultMessage(new PaymentResultDTO(paymentRequest.getOrderID(),
                    createdPaymentID, true));
        } else {
            paymentService.cancelPayment(createdPaymentID);
            paymentResultProducer.sendPaymentResultMessage(new PaymentResultDTO(paymentRequest.getOrderID(),
                    createdPaymentID, false));
        }
    }
}
