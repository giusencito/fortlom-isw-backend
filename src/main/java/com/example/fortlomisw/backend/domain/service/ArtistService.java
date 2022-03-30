package com.example.fortlomisw.backend.domain.service;

import com.example.fortlomisw.backend.domain.model.entity.Artist;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ArtistService {
    List<Artist> getAll();
    Page<Artist> getAll(Pageable pageable);
    Artist getById(Long artistId);
    Artist update(Long artistId, Artist request);
    ResponseEntity<?> delete(Long artistId);
    boolean existsByNombreUsuario(String nombreUsuario);
    boolean existsByEmail(String email);
    void save(Artist artist);
    Optional<Artist> getbyNombreUsuario(String nombreUsuario);
    Optional<Artist>getbyNombreUsuarioOrEmail(String nombreOremail);
    Artist create(Artist artist);
}
