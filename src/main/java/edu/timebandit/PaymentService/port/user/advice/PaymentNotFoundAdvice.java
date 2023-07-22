package edu.timebandit.PaymentService.port.user.advice;


import edu.timebandit.PaymentService.port.user.exception.PaymentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PaymentNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(value = PaymentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String paymentNotFoundHandler(PaymentNotFoundException ex){
        return ex.getMessage();
    }
}
