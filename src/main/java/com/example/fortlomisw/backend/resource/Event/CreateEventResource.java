package com.example.fortlomisw.backend.resource.Event;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateEventResource {

    private String EventName;
    private String EventDescription;
    private Long Likes;


}
