package edu.timebandit.PaymentService.port.user.exception;

public class PaymentNotFoundException extends RuntimeException{

    public PaymentNotFoundException(String id) {
        super("Payment with the ID: " + id + " could not be found.");
    }

}
