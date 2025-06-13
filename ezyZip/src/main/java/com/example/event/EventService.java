package com.example.event;

import com.example.event.Event;
import com.example.event.EventRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private final EventRepository repository;

    public EventService(EventRepository repository) {
        this.repository = repository;
    }

    public List<Event> getAll() {
        return repository.findAll();
    }

    public Optional<Event> getById(Long id) {
        return repository.findById(id);
    }

    public List<Event> getUpcoming() {
        return repository.findByDateAfter(LocalDate.now());
    }

    public Event save(Event event) {
        return repository.save(event);
    }

    public Event update(Long id, Event updated) {
        return repository.findById(id).map(event -> {
            event.setName(updated.getName());
            event.setDate(updated.getDate());
            event.setVenue(updated.getVenue());
            event.setDescription(updated.getDescription());
            return repository.save(event);
        }).orElse(null);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
