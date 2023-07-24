package edu.timebandit.PaymentService.port.checkout.consumer.impl;

import edu.timebandit.PaymentService.core.appservice.ProcessPaymentRequestAndNotifyCheckout;
import edu.timebandit.PaymentService.port.checkout.consumer.interfaces.IPaymentRequestConsumer;
import edu.timebandit.PaymentService.port.checkout.dtos.PaymentRequestDTO;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentRequestConsumer implements IPaymentRequestConsumer {

    private final static Logger logger = LoggerFactory.getLogger(PaymentRequestConsumer.class);

    @Autowired
    private ProcessPaymentRequestAndNotifyCheckout processPaymentRequest;

    @RabbitListener(queues = "request_payment_queue")
    public void receivePaymentRequestMessage(@Valid PaymentRequestDTO paymentRequest) {
        logger.info("Received message to create and process payment for order: {}", paymentRequest);

        processPaymentRequest.processAndNotify(paymentRequest);
    }
}
