package com.example.fortlomisw.backend.api;





import com.example.fortlomisw.backend.domain.model.entity.Event;
import com.example.fortlomisw.backend.domain.service.EventService;
import com.example.fortlomisw.backend.mapping.EventMapper;
import com.example.fortlomisw.backend.resource.Event.CreateEventResource;
import com.example.fortlomisw.backend.resource.Event.EventResource;
import com.example.fortlomisw.backend.resource.Event.UpdateEventResource;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private EventMapper mapper;

    @Autowired
    private ModelMapper mapping;

    @GetMapping("/events")
    public Page<EventResource> getAllEvents(Pageable pageable) {
        return mapper.modelListToPage(eventService.getAllEvents(), pageable);
    }
    @GetMapping("/event/{eventId}")
    public EventResource getEventById(@PathVariable Long eventId) {
        return mapper.toResource(eventService.getEventById(eventId));
    }
    @GetMapping("/artist/{artistId}/events")
    public Page<EventResource> getAllEventsByArtistId(@PathVariable Long artistId,Pageable pageable) {
        return mapper.modelListToPage(eventService.getEventsByArtistId(artistId), pageable);
    }
    @PostMapping("/artist/{artistId}/events")
    public EventResource createEvent(@PathVariable Long artistId,@RequestBody CreateEventResource request) {
        Event event = mapping.map(request, Event.class);
        return mapping.map(eventService.createEvent(artistId, event), EventResource.class);
    }
    @PutMapping("/event/{eventId}")
    public EventResource updateEvent(@PathVariable Long eventId, @RequestBody UpdateEventResource request) {
        return mapper.toResource(eventService.updateEvent(eventId, mapper.toModel(request)));
    }
    @DeleteMapping("/event/{eventId}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long eventId) {
        return eventService.deleteEvent(eventId);
    }

}
