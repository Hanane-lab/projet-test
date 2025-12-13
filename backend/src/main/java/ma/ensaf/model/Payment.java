package ma.ensaf.model;


import jakarta.persistence.*;
import java.time.Instant;


@Entity
public class Payment {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;


@OneToOne
private Ticket ticket;


private String paymentMethod; // STRIPE | PAYPAL
private Double amount;
private String status; // SUCCESS | FAILED
private Instant datePayment = Instant.now();


// getters/setters
public Long getId() { return id; }
public void setId(Long id) { this.id = id; }
public Ticket getTicket() { return ticket; }
public void setTicket(Ticket ticket) { this.ticket = ticket; }
public String getPaymentMethod() { return paymentMethod; }
public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
public Double getAmount() { return amount; }
public void setAmount(Double amount) { this.amount = amount; }
public String getStatus() { return status; }
public void setStatus(String status) { this.status = status; }
public Instant getDatePayment() { return datePayment; }
public void setDatePayment(Instant datePayment) { this.datePayment = datePayment; }
}