package ma.ensaf.controller;

import ma.ensaf.model.Ticket;
import ma.ensaf.model.User;
import ma.ensaf.repository.UserRepository;
import ma.ensaf.service.TicketService;
import ma.ensaf.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


import java.util.List;




@RestController
@RequestMapping("/ticket")
public class TicketController {


private final TicketService ticketService;
private final UserRepository userRepo;


public TicketController(TicketService ticketService, UserRepository userRepo) {
this.ticketService = ticketService;
this.userRepo = userRepo;
}


@PostMapping("/client/buy/{eventId}")
public Ticket buy(@PathVariable Long eventId, Authentication auth) throws Exception {
User u = userRepo.findByEmail(auth.getName()).orElseThrow();
return ticketService.createTicket(u, eventId);
}



}