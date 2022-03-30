package com.example.fortlomisw.backend.service;

import com.example.fortlomisw.backend.domain.model.entity.Multimedia;
import com.example.fortlomisw.backend.domain.persistence.MultimediaRepository;
import com.example.fortlomisw.backend.domain.persistence.PublicationRepository;
import com.example.fortlomisw.backend.domain.service.MultimediaService;
import com.example.fortlomisw.shared.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Validator;
import java.io.IOException;
import java.util.List;

@Service
public class MultimediaServiceImpl implements MultimediaService {


    private static final String ENTITY = "Multimedia";

    private final MultimediaRepository multimediaRepository;

    private final PublicationRepository publicationRepository;

    private final Validator validator;

    public MultimediaServiceImpl(MultimediaRepository multimediaRepository, PublicationRepository publicationRepository, Validator validator) {
        this.multimediaRepository = multimediaRepository;
        this.publicationRepository = publicationRepository;
        this.validator = validator;
    }


    @Override
    public List<Multimedia> getAll() {
        return multimediaRepository.findAll();
    }

    @Override
    public Page<Multimedia> getAll(Pageable pageable) {
        return multimediaRepository.findAll(pageable);
    }

    @Override
    public Multimedia getById(Long multimediaId) {
        return multimediaRepository.findById(multimediaId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, multimediaId));
    }

    @Override
    public Multimedia create(Long publicationId, Multimedia request, MultipartFile file) throws IOException {
        request.setContent(file.getBytes());

        return publicationRepository.findById(publicationId)
                .map(publications -> {
                    request.setPublication(publications);
                    return multimediaRepository.save(request);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Publication", publicationId));

    }





    @Override
    public Multimedia update(Long multimediaId, Multimedia request) {
        return null;
    }

    @Override
    public List<Multimedia> getMultimediaByPublicationId(Long multimediaId) {
        return multimediaRepository.findByPublicationId(multimediaId);
    }

    @Override
    public ResponseEntity<?> delete(Long multimediaId) {
        return multimediaRepository.findById(multimediaId).map(post -> {
            multimediaRepository.delete(post);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, multimediaId));
    }
}
