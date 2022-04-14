package com.example.fortlomisw.backend.service;

import com.example.fortlomisw.backend.Image.ImageModel;
import com.example.fortlomisw.backend.Image.ImageUtility;
import com.example.fortlomisw.backend.domain.model.entity.Multimedia;
import com.example.fortlomisw.backend.domain.persistence.MultimediaRepository;
import com.example.fortlomisw.backend.domain.persistence.PublicationRepository;
import com.example.fortlomisw.backend.domain.service.MultimediaService;
import com.example.fortlomisw.shared.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Validator;
import java.io.IOException;
import java.util.*;

@Service
public class MultimediaServiceImpl implements MultimediaService {






    private static final String ENTITY = "Multimedia";
    @Autowired
    private MultimediaRepository multimediaRepository;
    @Autowired
    private PublicationRepository publicationRepository;


    public MultimediaServiceImpl() {

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
    public ImageModel getImageDetails(Long MultimediaID) {
        Optional<Multimedia>dbImage=multimediaRepository.findById(MultimediaID);
        ImageModel imageModel= new ImageModel(dbImage.get().getId(),dbImage.get().getType(),ImageUtility.decompressImage(dbImage.get().getContent()));
        return imageModel;
    }

    @Override
    public ResponseEntity<byte[]> getImage(Long MultimediaID) {
        Optional<Multimedia>dbImage=multimediaRepository.findById(MultimediaID);
        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(dbImage.get().getType()))
                .body(ImageUtility.decompressImage(dbImage.get().getContent()));


    }

    @Override
    public void create(Long publicationId, MultipartFile file) throws IOException {
        Multimedia request = new Multimedia();
        request.setType(file.getContentType());

        request.setContent(ImageUtility.compressImage(file.getBytes()));

         publicationRepository.findById(publicationId)
                .map(publications -> {
                    request.setPublication(publications);
                     multimediaRepository.save(request);
                    return ResponseEntity.ok().build();
                })
                 .orElseThrow(() -> new ResourceNotFoundException("Publication", publicationId));


    }

    @Override
    public ResponseEntity<ByteArrayResource> download(Long filenameId) {
        Multimedia multimedia=getById(filenameId);
        multimedia.setContent(ImageUtility.decompressImage(multimedia.getContent()));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(multimedia.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\""+multimedia.getId()+"\"")
                .body(new ByteArrayResource(multimedia.getContent()));
    }


    @Override
    public Multimedia update(Long multimediaId, Multimedia request) {
        return null;
    }

    @Override
    public List<ImageModel> getMultimediaByPublicationId(Long multimediaId) {
        List<Multimedia> multimedias=multimediaRepository.findByPublicationId(multimediaId);
        List<ImageModel>imageModels = new ArrayList<>() ;
        for (int i=0;i<multimedias.size();i++){
            Multimedia multimedia=multimedias.get(i);
            ImageModel imageModel= new ImageModel(multimedia.getId(),multimedia.getType(),ImageUtility.decompressImage(multimedia.getContent()));
            imageModels.add(imageModel);
        }
        return imageModels;
    }

    @Override
    public ResponseEntity<?> delete(Long multimediaId) {
        return multimediaRepository.findById(multimediaId).map(post -> {
            multimediaRepository.delete(post);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, multimediaId));
    }
}
