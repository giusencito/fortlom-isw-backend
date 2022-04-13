package com.example.fortlomisw.backend.security.controller;
import com.example.fortlomisw.backend.security.Dto.LoginUser;
import com.example.fortlomisw.backend.security.Dto.NewArtist;
import com.example.fortlomisw.backend.security.Dto.NewFanatic;
import com.example.fortlomisw.backend.security.jwt.jwtDto;
import com.example.fortlomisw.backend.security.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;

@RestController
@RequestMapping("/auth")
public class authcontroller {


    @Autowired
    AuthService authService;


    @PostMapping("/fanatic")
    public ResponseEntity<?> registerfanatic(@Valid @RequestBody NewFanatic request, BindingResult bindingResult) {

        return  authService.registerfanatic(request,bindingResult);


    }
    @PostMapping("/artist")
    public ResponseEntity<?> registerartist(@Valid @RequestBody NewArtist request, BindingResult bindingResult) {

        return  authService.registerartist(request,bindingResult);


    }
    @PostMapping("/login")
    public ResponseEntity<jwtDto>login(@Valid @RequestBody LoginUser loginUser, BindingResult bindingResult){

        return authService.login(loginUser,bindingResult);
    }
}
