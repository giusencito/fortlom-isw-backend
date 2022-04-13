package com.example.fortlomisw.backend.api;
import com.example.fortlomisw.backend.Image.ImageModel;
import com.example.fortlomisw.backend.domain.model.entity.Multimedia;
import com.example.fortlomisw.backend.domain.service.MultimediaService;
import com.example.fortlomisw.backend.mapping.MultimediaMapper;
import com.example.fortlomisw.backend.resource.Multimedia.CreateMultimediaResource;
import com.example.fortlomisw.backend.resource.Multimedia.MultimediaResource;
import com.example.fortlomisw.backend.resource.Multimedia.UpdateMultimediaResource;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity.BodyBuilder;

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
    @GetMapping("/multimedias/image/info/{multimediaId}")
    public ImageModel getImageDetails (@PathVariable("multimediaId") Long multimediaId) throws IOException{
        return  multimediaService.getImageDetails(multimediaId);
    }
    @GetMapping("/multimedias/image/{multimediaId}")
    public ResponseEntity<byte[]> getImage (@PathVariable("multimediaId") Long multimediaId) throws IOException{
        return  multimediaService.getImage(multimediaId);
    }



    @PostMapping("/publications/{publicationId}/multimedias")
    public void createComment( @PathVariable Long publicationId,@RequestParam("file") MultipartFile file) throws IOException {
          multimediaService.create(publicationId,file);
    }

    @GetMapping("/publications/{publicationId}/multimedias")
    public List<ImageModel> getAllmultimediasByPublicationId(@PathVariable Long publicationId, Pageable pageable) {
        return multimediaService.getMultimediaByPublicationId(publicationId);
    }

    @GetMapping("download/{filenameId}")
    public ResponseEntity<ByteArrayResource> downloadFiles(@PathVariable Long  filenameId ){

        return multimediaService.download(filenameId);
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
