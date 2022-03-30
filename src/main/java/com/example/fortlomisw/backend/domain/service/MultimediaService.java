package com.example.fortlomisw.backend.domain.service;
import com.example.fortlomisw.backend.domain.model.entity.Multimedia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;
public interface MultimediaService {
    List<Multimedia> getAll();
    Page<Multimedia> getAll(Pageable pageable);
    Multimedia getById(Long multimediaId);
    Multimedia create(Long multimediaId, Multimedia multimedia, MultipartFile file) throws IOException;
    Multimedia update(Long multimediaId, Multimedia request);
    List<Multimedia> getMultimediaByPublicationId(Long multimediaId);
    ResponseEntity<?> delete(Long commentId);
}
