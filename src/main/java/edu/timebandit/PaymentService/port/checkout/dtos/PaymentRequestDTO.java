package edu.timebandit.PaymentService.port.checkout.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequestDTO {

        private String orderID;

        private double totalPrice;

        private String paymentMethod;
}
