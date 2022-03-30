package com.example.fortlomisw.backend.resource.PublicationComment;



import com.example.fortlomisw.backend.resource.Person.PersonResource;
import com.example.fortlomisw.backend.resource.Publication.PublicationResource;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class PublicationCommentResource {

    private Long id;

    private String CommentDescription;

    private Date registerdate;

    private PersonResource user;

    private PublicationResource publicationResource;


}
