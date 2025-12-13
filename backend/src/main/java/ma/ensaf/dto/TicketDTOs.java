package ma.ensaf.dto;


import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TicketDTOs {
    
    public static class PurchaseTicketRequest {
        private Long eventId;
        private Integer quantity = 1;
        private String paymentMethod;
    }
    
    public static class TicketResponse {
        private Long id;
        private String ticketCode;
        private Long eventId;
        private String eventTitle;
        private LocalDateTime eventDate;
        private String eventLocation;
        private BigDecimal pricePaid;
        private LocalDateTime purchaseDate;
        private String status;
        private String qrCodeUrl;
        private String paymentStatus;
    }
    
    public static class TicketValidationRequest {
        private String ticketCode;
    }
    
    public static class TicketValidationResponse {
        private boolean valid;
        private String message;
        private TicketResponse ticket;
    }
}
