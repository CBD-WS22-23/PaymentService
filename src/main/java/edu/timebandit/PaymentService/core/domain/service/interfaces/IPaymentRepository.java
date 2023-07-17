package edu.timebandit.PaymentService.core.domain.service.interfaces;

import edu.timebandit.PaymentService.core.appservice.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IPaymentRepository extends CrudRepository<Payment, UUID> {
}
