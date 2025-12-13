package ma.ensaf.dto;


import java.math.BigDecimal;
import java.time.LocalDateTime;

public class EventDTOs {
    
    public static class CreateEventRequest {
        private String title;
        private String description;
        private String location;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private String category;
        private Integer maxParticipants;
        private BigDecimal price;
        private String imageUrl;
    }
    
    public static class UpdateEventRequest {
        private String title;
        private String description;
        private String location;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private String category;
        private Integer maxParticipants;
        private BigDecimal price;
        private String imageUrl;
        private String status;
    }
    
    public static class EventResponse {
        private Long id;
        private String title;
        private String description;
        private String location;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private String category;
        private Integer maxParticipants;
        private Integer currentParticipants;
        private BigDecimal price;
        private String imageUrl;
        private String organizerName;
        private String status;
        private LocalDateTime createdAt;
        private boolean isFull;
        private boolean hasAvailableSpots;
    }
    
    public static class EventDetailsResponse extends EventResponse {
        private Long organizerId;
        private boolean isFavorite;
        private Long ticketsSold;
    }
}