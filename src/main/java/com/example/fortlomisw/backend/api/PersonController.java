package com.example.fortlomisw.backend.api;


import com.example.fortlomisw.backend.Image.ImageModel;
import com.example.fortlomisw.backend.domain.service.PersonService;
import com.example.fortlomisw.backend.mapping.MultimediaMapper;
import com.example.fortlomisw.backend.mapping.PersonMapper;
import com.example.fortlomisw.backend.resource.Multimedia.MultimediaResource;
import com.example.fortlomisw.backend.resource.Person.PersonResource;
import com.example.fortlomisw.backend.resource.Person.UpdatePersonResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1")
public class PersonController {

    @Autowired
    PersonService personService;


    @Autowired
    private PersonMapper mapper;

    @GetMapping("/users/{userID}")
    public PersonResource getCommentById(@PathVariable("userID") Long userID) {
        return mapper.toResource(personService.getById(userID));
    }
    @PutMapping("/users/{userID}/updatephoto")
    public void createComment( @PathVariable Long userID,@RequestParam("file") MultipartFile file) throws IOException {
        personService.updatephoto(userID,file);
    }

    @PutMapping("/users/changeprofile/{userId}")
    public PersonResource updateprofile(@PathVariable Long userId, @RequestBody UpdatePersonResource request) {
        return mapper.toResource(personService.updateprofile(userId, mapper.toModel(request)));
    }
    @PutMapping("/users/changepassword/{userId}")
    public PersonResource updatepassword(@PathVariable Long userId, @RequestBody UpdatePersonResource request) {
        return mapper.toResource(personService.updatepassword(userId, mapper.toModel(request)));
    }


    @GetMapping("/users/onlyimage/{userID}")
    public ResponseEntity<byte[]> getImage (@PathVariable("userID") Long userID) throws IOException{
        return  personService.getprofileimage(userID);
    }
    @GetMapping("/users/image/{userID}")
    public ImageModel getImagecontent (@PathVariable("userID") Long userID) throws IOException{
        return  personService.getImageDetails(userID);
    }



}
