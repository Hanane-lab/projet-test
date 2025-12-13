package ma.ensaf.service;

import ma.ensaf.model.Event;
import ma.ensaf.model.Ticket;
import ma.ensaf.model.User;
import ma.ensaf.repository.EventRepository;
import ma.ensaf.repository.TicketRepository;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.stereotype.Service;


import java.io.ByteArrayOutputStream;
import java.util.Base64;


@Service
public class TicketService {


private final TicketRepository ticketRepo;
private final EventRepository eventRepo;


public TicketService(TicketRepository ticketRepo, EventRepository eventRepo) {
this.ticketRepo = ticketRepo;
this.eventRepo = eventRepo;
}


public Ticket createTicket(User user, Long eventId) throws Exception {


Event e = eventRepo.findById(eventId).orElseThrow();
if (e.getAvailableSeats() <= 0) throw new RuntimeException("Plus de places");


Ticket t = new Ticket();
t.setUser(user);
t.setEvent(e);


// QR CODE
String qrText = "TICKET-" + user.getId() + "-" + eventId + "-" + System.currentTimeMillis();
BitMatrix matrix = new MultiFormatWriter().encode(qrText, BarcodeFormat.QR_CODE, 300, 300);
ByteArrayOutputStream bos = new ByteArrayOutputStream();
MatrixToImageWriter.writeToStream(matrix, "PNG", bos);
String base64 = Base64.getEncoder().encodeToString(bos.toByteArray());


t.setQrCode(base64);


// mise à jour places
e.setAvailableSeats(e.getAvailableSeats() - 1);
eventRepo.save(e);


return ticketRepo.save(t);
}
}