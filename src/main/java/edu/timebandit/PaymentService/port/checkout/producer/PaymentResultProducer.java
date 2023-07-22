package edu.timebandit.PaymentService.port.checkout.producer;

import edu.timebandit.PaymentService.port.checkout.dtos.PaymentResultDTO;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentResultProducer implements IPaymentResultProducer{

    @Value("payment_exchange")
    private String exchange;

    @Value("payment_result_routing_key")
    private String paymentResultRoutingKey;

    private final static Logger logger = LoggerFactory.getLogger(PaymentResultProducer.class);

    private final RabbitTemplate rabbitTemplate;

    public PaymentResultProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendPaymentResultMessage(@Valid PaymentResultDTO paymentResult) {
        logger.info("Sending message to notify payment result for order: {}", paymentResult);
        rabbitTemplate.convertAndSend(exchange, paymentResultRoutingKey, paymentResult);
    }
}
