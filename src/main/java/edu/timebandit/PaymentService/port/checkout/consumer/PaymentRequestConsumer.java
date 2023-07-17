package edu.timebandit.PaymentService.port.checkout.consumer;

import edu.timebandit.PaymentService.core.domain.service.interfaces.IPaymentService;
import edu.timebandit.PaymentService.port.checkout.dtos.PaymentRequestDTO;
import edu.timebandit.PaymentService.port.checkout.dtos.PaymentResultDTO;
import edu.timebandit.PaymentService.port.checkout.producer.PaymentResultProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PaymentRequestConsumer {

    private final static Logger logger = LoggerFactory.getLogger(PaymentRequestConsumer.class);

    @Autowired
    private PaymentResultProducer paymentResultProducer;

    @Autowired
    private IPaymentService paymentService;

    Random randomizer = new Random();

    @RabbitListener(queues = "request_payment_queue")
    public void receivePaymentRequestMessage(PaymentRequestDTO paymentRequest) {
        logger.info("Received message to create and process request payment for order: {}", paymentRequest);

        String paymentID = paymentService.createPayment(paymentRequest.getOrderID(), paymentRequest.getTotalPrice(),
                paymentRequest.getPaymentMethod());


        randomizer.setSeed(System.currentTimeMillis());
        boolean paymentSuccessful = randomizer.nextBoolean();

        if (paymentSuccessful) {
            paymentService.processPayment(paymentID);
            paymentResultProducer.sendPaymentResultMessage(new PaymentResultDTO(paymentRequest.getOrderID(),
                    paymentID, true));
        } else {
            paymentService.cancelPayment(paymentID);
            paymentResultProducer.sendPaymentResultMessage(new PaymentResultDTO(paymentRequest.getOrderID(),
                    paymentID, false));
        }
    }
}
