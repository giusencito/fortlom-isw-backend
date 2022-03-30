package com.example.fortlomisw.backend.service;

import com.example.fortlomisw.backend.domain.model.entity.Event;
import com.example.fortlomisw.backend.domain.persistence.ArtistRepository;
import com.example.fortlomisw.backend.domain.persistence.EventRepository;
import com.example.fortlomisw.backend.domain.persistence.FanaticRepository;
import com.example.fortlomisw.backend.domain.service.EventService;
import com.example.fortlomisw.shared.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {


    private static final String ENTITY = "Event";
    private static final String ENTITY2 = "Artist";
    private final EventRepository eventRepository;
    private final ArtistRepository artistRepository;
    private final Validator validator;

    public EventServiceImpl(EventRepository eventRepository, ArtistRepository artistRepository, Validator validator) {
        this.eventRepository = eventRepository;
        this.artistRepository = artistRepository;
        this.validator = validator;
    }


    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Page<Event> getAllEvents(Pageable pageable) {
        return eventRepository.findAll(pageable);
    }

    @Override
    public Event getEventById(Long eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, eventId));
    }

    @Override
    public Event createEvent(Long artistId, Event request) {

        return artistRepository.findById(artistId)
                .map(artists -> {
                    request.setArtist(artists);
                    return eventRepository.save(request);
                })
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY2, artistId));
    }

    @Override
    public Event updateEvent(Long eventId, Event request) {
        return null;
    }

    @Override
    public List<Event> getEventsByArtistId(Long artistId) {
        return eventRepository.findByArtistId(artistId);

    }

    @Override
    public ResponseEntity<?> deleteEvent(Long eventId) {
        return eventRepository.findById(eventId).map(event -> {
            eventRepository.delete(event);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, eventId));
    }
}
