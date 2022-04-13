package com.example.fortlomisw.backend.security.controller;


import com.example.fortlomisw.backend.security.Dto.ChangePassword;
import com.example.fortlomisw.backend.security.Dto.EmailValues;
import com.example.fortlomisw.backend.security.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/email-password")
@CrossOrigin
public class emailcontroller {

    @Autowired
    EmailService emailService;


    @PostMapping("/send-email")
    public ResponseEntity<?> sendEmailTemplate(@RequestBody EmailValues dto) {
        return emailService.sendEmailTemplate(dto);
    }
    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePassword dto, BindingResult bindingResult) {

        return emailService.changePassword(dto, bindingResult);



    }

}
