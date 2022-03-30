package com.example.fortlomisw.backend.api;
import com.example.fortlomisw.backend.domain.model.entity.Multimedia;
import com.example.fortlomisw.backend.domain.service.MultimediaService;
import com.example.fortlomisw.backend.mapping.MultimediaMapper;
import com.example.fortlomisw.backend.resource.Multimedia.CreateMultimediaResource;
import com.example.fortlomisw.backend.resource.Multimedia.MultimediaResource;
import com.example.fortlomisw.backend.resource.Multimedia.UpdateMultimediaResource;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/api/v1")
public class MultimediaController {


    @Autowired
    private MultimediaService multimediaService;

    @Autowired
    private MultimediaMapper mapper;

    @Autowired
    private ModelMapper mapping;


    @GetMapping("/multimedias")
    public Page<MultimediaResource> getAllMultimedias(Pageable pageable) {
        return mapper.modelListToPage(multimediaService.getAll(), pageable);
    }

    @GetMapping("/multimedias/{multimediaId}")
    public MultimediaResource getCommentById(@PathVariable("multimediaId") Long multimediaId) {
        return mapper.toResource(multimediaService.getById(multimediaId));
    }

    @PostMapping("/publications/{publicationId}/multimedias")
    public MultimediaResource createComment( @PathVariable Long publicationId, @RequestBody CreateMultimediaResource request,@RequestParam("file") MultipartFile file) throws IOException {
        Multimedia comment = mapping.map(request, Multimedia.class);
        return mapping.map(multimediaService.create(publicationId, comment,file), MultimediaResource.class);
    }

    @GetMapping("/publications/{publicationId}/multimedias")
    public Page<MultimediaResource> getAllmultimediasByPublicationId(@PathVariable Long publicationId,Pageable pageable) {
        return mapper.modelListToPage(multimediaService.getMultimediaByPublicationId(publicationId), pageable);
    }

    @PutMapping("/multimedias/{multimediaId}")
    public MultimediaResource updateMultimedia(@PathVariable Long multimediaId, @RequestBody UpdateMultimediaResource request) {
        return mapper.toResource(multimediaService.update(multimediaId, mapper.toModel(request)));
    }

    @DeleteMapping("/multimedias/{multimediaId}")
    public ResponseEntity<?> deleteMultimedia(@PathVariable Long commentId) {
        return multimediaService.delete(commentId);
    }


}
