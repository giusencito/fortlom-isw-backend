package com.example.fortlomisw.backend.resource.Artist;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateArtistResource {

    private String UserName;

    private String UserRealName;

    private String UserLastName;

    private String UserEmail;

    private String UserPassword;

    private Long ArtistFollowers;
}
