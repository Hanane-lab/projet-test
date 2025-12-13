package ma.ensaf.model;



import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
public class Event {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;


private String title;
@Column(length = 4000)
private String description;
private LocalDateTime dateEvent;
private String location;
private Double price;
private Integer capacity;
private Integer availableSeats;
private String imageUrl;
private String status = "VISIBLE"; // or HIDDEN


@ManyToOne
private User organizer;


// getters/setters
// ... (omitted for brevity, implement as needed)
public Long getId() { return id; }
public void setId(Long id) { this.id = id; }
public String getTitle() { return title; }
public void setTitle(String title) { this.title = title; }
public String getDescription() { return description; }
public void setDescription(String description) { this.description = description; }
public LocalDateTime getDateEvent() { return dateEvent; }
public void setDateEvent(LocalDateTime dateEvent) { this.dateEvent = dateEvent; }
public String getLocation() { return location; }
public void setLocation(String location) { this.location = location; }
public Double getPrice() { return price; }
public void setPrice(Double price) { this.price = price; }
public Integer getCapacity() { return capacity; }
public void setCapacity(Integer capacity) { this.capacity = capacity; }
public Integer getAvailableSeats() { return availableSeats; }
public void setAvailableSeats(Integer availableSeats) { this.availableSeats = availableSeats; }
public String getImageUrl() { return imageUrl; }
public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
public String getStatus() { return status; }
public void setStatus(String status) { this.status = status; }
public User getOrganizer() { return organizer; }
public void setOrganizer(User organizer) { this.organizer = organizer; }
}