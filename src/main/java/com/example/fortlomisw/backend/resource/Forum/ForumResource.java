package com.example.fortlomisw.backend.resource.Forum;

import com.example.fortlomisw.backend.resource.Person.PersonResource;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ForumResource {
    private Long id;
    private String ForumName;
    private String ForumDescription;
    private PersonResource user;
}
