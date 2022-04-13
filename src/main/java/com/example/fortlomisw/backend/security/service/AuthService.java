package com.example.fortlomisw.backend.security.service;

import com.example.fortlomisw.backend.domain.model.entity.Artist;
import com.example.fortlomisw.backend.domain.model.entity.Fanatic;
import com.example.fortlomisw.backend.domain.model.entity.Rol;
import com.example.fortlomisw.backend.domain.model.enumeration.RolNombre;
import com.example.fortlomisw.backend.domain.service.ArtistService;
import com.example.fortlomisw.backend.domain.service.FanaticService;
import com.example.fortlomisw.backend.domain.service.RolService;
import com.example.fortlomisw.backend.security.Dto.LoginUser;
import com.example.fortlomisw.backend.security.Dto.NewArtist;
import com.example.fortlomisw.backend.security.Dto.NewFanatic;
import com.example.fortlomisw.backend.security.jwt.jwtDto;
import com.example.fortlomisw.backend.security.jwt.jwtProvider;
import com.example.fortlomisw.shared.exception.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthService {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    FanaticService fanaticService;

    @Autowired
    ArtistService artistService;

    @Autowired
    jwtProvider jwtProvider;

    @Autowired
    RolService rolService;


    public ResponseEntity<?> registerfanatic(NewFanatic request, BindingResult bindingResult){
        if (bindingResult.hasErrors()){


            return new ResponseEntity(new Message("campos mal puestos o email invalido"), HttpStatus.BAD_REQUEST);
        }
        if(fanaticService.existsByNombreUsuario(request.getUsername())){
            return new ResponseEntity(new Message("ya existe nombre"), HttpStatus.BAD_REQUEST);

        }
        if (fanaticService.existsByEmail(request.getEmail())){
            return new ResponseEntity(new Message("ya existe email"), HttpStatus.BAD_REQUEST);
        }
        Fanatic fanatic= new Fanatic();
        fanatic.setUsername(request.getUsername());
        fanatic.setRealname(request.getRealname());
        fanatic.setLastname(request.getLastname());
        fanatic.setEmail(request.getEmail());
        fanatic.setFanaticalias(request.getFanaticalias());
        fanatic.setPassword(passwordEncoder.encode(request.getPassword()));
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.findByName(RolNombre.Role_Fanatic).get());
        fanatic.setRoles(roles);
        fanaticService.save(fanatic);
        return new ResponseEntity(new Message("new fanatic saved"),HttpStatus.CREATED);

    }



    public ResponseEntity<?> registerartist(NewArtist request, BindingResult bindingResult){
        if (bindingResult.hasErrors()){


            return new ResponseEntity(new Message("campos mal puestos o email invalido"), HttpStatus.BAD_REQUEST);
        }
        if(artistService.existsByNombreUsuario(request.getUsername())){
            return new ResponseEntity(new Message("ya existe nombre"), HttpStatus.BAD_REQUEST);

        }
        if (artistService.existsByEmail(request.getEmail())){
            return new ResponseEntity(new Message("ya existe email"), HttpStatus.BAD_REQUEST);
        }
        Artist artist= new Artist();
        artist.setUsername(request.getUsername());
        artist.setRealname(request.getRealname());
        artist.setLastname(request.getLastname());
        artist.setEmail(request.getEmail());
        artist.setArtistfollowers((long)0 );
        artist.setPassword(passwordEncoder.encode(request.getPassword()));
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.findByName(RolNombre.Role_Artist).get());
        artist.setRoles(roles);
        artistService.save(artist);
        return new ResponseEntity(new Message("new artist saved"),HttpStatus.CREATED);

    }


    public ResponseEntity<jwtDto>login(LoginUser loginUser, BindingResult bindingResult){

        if(bindingResult.hasErrors())
            return new ResponseEntity(new Message("campos mal puestos"), HttpStatus.BAD_REQUEST);
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getNombreUsuario(), loginUser.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        jwtDto jwtDto = new jwtDto(jwt);
        return new ResponseEntity(jwtDto, HttpStatus.OK);






    }












}
