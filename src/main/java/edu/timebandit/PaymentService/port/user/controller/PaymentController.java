package edu.timebandit.PaymentService.port.user.controller;

import edu.timebandit.PaymentService.core.domain.model.Payment;
import edu.timebandit.PaymentService.core.domain.service.interfaces.IPaymentService;
import edu.timebandit.PaymentService.port.user.exception.PaymentNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class PaymentController {

    @Autowired
    private IPaymentService paymentService;

    @Operation(summary = "Get all payments")
    @GetMapping(path = "/payment")
    public Iterable<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @Operation(summary = "Get a payment by id")
    @GetMapping(path = "/payment/{paymentID}")
    public Payment getPayment(@PathVariable String paymentID) {
        Payment requestedPayment = paymentService.getPaymentByID(paymentID);
        if (requestedPayment == null) {
            throw new PaymentNotFoundException(paymentID);
        }
        return requestedPayment;
    }

    @Operation(summary = "Delete a payment by id")
    @DeleteMapping(path = "/payment/{paymentID}/")
    public void deletePayment(@PathVariable String paymentID) {
        paymentService.deletePayment(paymentID);
    }

}
