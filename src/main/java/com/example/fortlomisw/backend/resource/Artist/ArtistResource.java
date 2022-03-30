package com.example.fortlomisw.backend.resource.Artist;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ArtistResource {

    private Long id;

    private String UserName;

    private String UserRealName;

    private String UserLastName;

    private String UserEmail;

    private String UserPassword;

    private byte[] content;

    private Long ArtistFollowers;
}
