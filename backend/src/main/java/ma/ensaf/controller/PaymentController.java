package ma.ensaf.controller;

import ma.ensaf.model.Payment;
import ma.ensaf.model.Ticket;
import ma.ensaf.repository.PaymentRepository;
import ma.ensaf.repository.TicketRepository;
import ma.ensaf.service.PaymentService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Map;
import java.util.Optional;


@RestController
public class PaymentController {
private final PaymentRepository paymentRepository;
private final TicketRepository ticketRepository;


public PaymentController(PaymentRepository paymentRepository, TicketRepository ticketRepository) {
this.paymentRepository = paymentRepository;
this.ticketRepository = ticketRepository;
}


// Simulation de paiement
@PostMapping("/payment/pay")
public ResponseEntity<?> pay(@RequestBody Map<String, String> body) {
Long ticketId = Long.valueOf(body.get("ticketId"));
String method = body.getOrDefault("method", "STRIPE");
Optional<Ticket> tOpt = ticketRepository.findById(ticketId);
if (tOpt.isEmpty()) return ResponseEntity.badRequest().body(Map.of("error", "ticket not found"));
Ticket t = tOpt.get();
Payment p = new Payment();
p.setTicket(t);
p.setAmount(t.getEvent().getPrice());
p.setPaymentMethod(method);
p.setStatus("SUCCESS"); // simulated
paymentRepository.save(p);
return ResponseEntity.ok(Map.of("status", "SUCCESS", "paymentId", p.getId()));
}
}