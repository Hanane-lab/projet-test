package ma.ensaf.controller;

import ma.ensaf.model.Event;
import ma.ensaf.model.User;
import ma.ensaf.service.EventService;
import ma.ensaf.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;


@RestController
public class EventController {


private final EventService eventService;


public EventController(EventService eventService) {
this.eventService = eventService;
}


@GetMapping("/public")
public List<Event> listPublic() {
return eventService.findAllVisible();
}


@PostMapping("/admin/create")
public Event create(@RequestBody Event e) {
return eventService.create(e);
}


@PutMapping("/admin/update/{id}")
public Event update(@PathVariable Long id, @RequestBody Event e) {
return eventService.update(id, e);
}}