package ma.ensaf.service;

import ma.ensaf.model.Payment;
import ma.ensaf.model.Ticket;
import ma.ensaf.repository.PaymentRepository;
import org.springframework.stereotype.Service;


@Service
public class PaymentService {


private final PaymentRepository payRepo;


public PaymentService(PaymentRepository payRepo) {
this.payRepo = payRepo;
}


public Payment simulatePay(Ticket t, String method) {
Payment p = new Payment();
p.setTicket(t);
p.setPaymentMethod(method);
p.setAmount(t.getEvent().getPrice());
p.setStatus("SUCCESS");
return payRepo.save(p);
}
}
