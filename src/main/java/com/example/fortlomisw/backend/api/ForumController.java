package com.example.fortlomisw.backend.api;


import com.example.fortlomisw.backend.domain.model.entity.Forum;
import com.example.fortlomisw.backend.domain.service.ForumService;
import com.example.fortlomisw.backend.mapping.ForumMapper;
import com.example.fortlomisw.backend.resource.Forum.CreateForumResource;
import com.example.fortlomisw.backend.resource.Forum.ForumResource;
import com.example.fortlomisw.backend.resource.Forum.UpdateForumResource;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/v1")
public class ForumController {

    @Autowired
    private ForumService forumService;

    @Autowired
    private ForumMapper mapper;

    @Autowired
    private ModelMapper mapping;

    @GetMapping("/forums")
    public Page<ForumResource> getAllForums(Pageable pageable) {
        return mapper.modelListToPage(forumService.getAllForums(), pageable);
    }
    @GetMapping("/forums/{forumId}")
    public ForumResource getForumById(@PathVariable Long forumId) {
        return mapper.toResource(forumService.getForumById(forumId));
    }
    @GetMapping("/user/{usersId}/forums")
    public Page<ForumResource> getAllForumsByusersId(@PathVariable Long usersId,Pageable pageable) {
        return mapper.modelListToPage(forumService.getForumsByUserId(usersId), pageable);
    }
    @PostMapping("/user/{usersId}/forums")
    public ForumResource createForum(@PathVariable Long usersId,@RequestBody CreateForumResource request) {
        Forum forum = mapping.map(request, Forum.class);
        return mapping.map(forumService.createForum(usersId, forum), ForumResource.class);
    }
    @PutMapping("/forums/{forumId}")
    public ForumResource updateForum(@PathVariable Long forumId, @RequestBody UpdateForumResource request) {
        return mapper.toResource(forumService.updateForum(forumId, mapper.toModel(request)));
    }
    @DeleteMapping("/forums/{forumId}")
    public ResponseEntity<?> deleteForum(@PathVariable Long forumId) {
        return forumService.deleteForum(forumId);
    }









}
