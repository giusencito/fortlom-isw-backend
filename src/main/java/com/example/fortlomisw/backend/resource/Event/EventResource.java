package com.example.fortlomisw.backend.resource.Event;

import com.example.fortlomisw.backend.resource.Artist.ArtistResource;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventResource {
    private Long id;
    private String EventName;
    private String EventDescription;
    private Long Likes;
    private ArtistResource artist;
}
