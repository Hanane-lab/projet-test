package ma.ensaf.model;



import jakarta.persistence.*;
import java.time.Instant;


@Entity
public class Ticket {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;


@ManyToOne
private User user;


@ManyToOne
private Event event;


@Column(length = 2000)
private String qrCode; // base64 or simple token


private Instant purchaseDate = Instant.now();


private String status = "VALID";


// getters/setters
public Long getId() { return id; }
public void setId(Long id) { this.id = id; }
public User getUser() { return user; }
public void setUser(User user) { this.user = user; }
public Event getEvent() { return event; }
public void setEvent(Event event) { this.event = event; }
public String getQrCode() { return qrCode; }
public void setQrCode(String qrCode) { this.qrCode = qrCode; }
public Instant getPurchaseDate() { return purchaseDate; }
public void setPurchaseDate(Instant purchaseDate) { this.purchaseDate = purchaseDate; }
public String getStatus() { return status; }
public void setStatus(String status) { this.status = status; }
}