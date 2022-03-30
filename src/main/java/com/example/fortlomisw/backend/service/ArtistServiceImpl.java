package com.example.fortlomisw.backend.service;

import com.example.fortlomisw.backend.domain.model.entity.Artist;
import com.example.fortlomisw.backend.domain.persistence.ArtistRepository;
import com.example.fortlomisw.backend.domain.service.ArtistService;
import com.example.fortlomisw.shared.exception.ResourceNotFoundException;
import com.example.fortlomisw.shared.exception.ResourceValidationException;
import com.example.fortlomisw.shared.exception.ResourcePerzonalized;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
@Service
public class ArtistServiceImpl implements ArtistService {


    private static final String ENTITY = "Artist";

    private final ArtistRepository artistRepository;

    private final Validator validator;

    public ArtistServiceImpl(ArtistRepository artistRepository, Validator validator) {
        this.artistRepository = artistRepository;
        this.validator = validator;
    }


    @Override
    public List<Artist> getAll() {
        return artistRepository.findAll();
    }

    @Override
    public Page<Artist> getAll(Pageable pageable) {
        return artistRepository.findAll(pageable);
    }

    @Override
    public Artist getById(Long artistId) {
        return artistRepository.findById(artistId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, artistId));
    }

    @Override
    public Artist update(Long artistId, Artist request) {


        Set<ConstraintViolation<Artist>> violations = validator.validate(request);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return artistRepository.findById(artistId).map(artist ->
                artistRepository.save(
                        artist.withArtistfollowers(request.getArtistfollowers())

                )

        ).orElseThrow(() -> new ResourceNotFoundException(ENTITY, artistId));
    }

    @Override
    public ResponseEntity<?> delete(Long artistId) {
        return artistRepository.findById(artistId).map(post -> {
            artistRepository.delete(post);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, artistId));
    }

    @Override
    public boolean existsByNombreUsuario(String nombreUsuario) {
        return artistRepository.existsByUsername(nombreUsuario);
    }

    @Override
    public boolean existsByEmail(String email) {
         return artistRepository.existsByEmail(email);
    }

    @Override
    public void save(Artist artist) {
         artistRepository.save(artist);
    }

    @Override
    public Optional<Artist> getbyNombreUsuario(String nombreUsuario) {
        return artistRepository.findByUsername(nombreUsuario);
    }

    @Override
    public Optional<Artist> getbyNombreUsuarioOrEmail(String nombreOremail) {
        return artistRepository.findByUsernameOrEmail(nombreOremail,nombreOremail);
    }

    @Override
    public Artist create(Artist artist) {
        Set<ConstraintViolation<Artist>> violations = validator.validate(artist);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        if(artistRepository.existsByUsername(artist.getUsername()))
            throw  new ResourcePerzonalized("ya exsite este nombre de usuario");
        if (artistRepository.existsByEmail(artist.getEmail()))
            throw  new ResourcePerzonalized("ya exsite este correo electronico");

        return artistRepository.save(artist);
    }
}
