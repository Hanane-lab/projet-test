package ma.ensaf.service;

import ma.ensaf.model.Event;
import ma.ensaf.model.User;
import ma.ensaf.repository.EventRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
public class EventService {
private final EventRepository eventRepo;




public EventService(EventRepository eventRepo) {
this.eventRepo = eventRepo;
}


public Event create(Event e) {
e.setAvailableSeats(e.getCapacity());
return eventRepo.save(e);
}


public List<Event> findAllVisible() {
return eventRepo.findByStatus("VISIBLE");
}


public Event update(Long id, Event newEvent) {
Event e = eventRepo.findById(id).orElseThrow();
e.setTitle(newEvent.getTitle());
e.setDescription(newEvent.getDescription());
e.setDateEvent(newEvent.getDateEvent());
e.setPrice(newEvent.getPrice());
e.setCapacity(newEvent.getCapacity());
e.setAvailableSeats(newEvent.getAvailableSeats());
e.setLocation(newEvent.getLocation());
e.setImageUrl(newEvent.getImageUrl());
return eventRepo.save(e);
}}