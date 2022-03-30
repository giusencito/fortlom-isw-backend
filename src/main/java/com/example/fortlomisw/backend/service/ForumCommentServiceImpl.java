package com.example.fortlomisw.backend.service;


import com.example.fortlomisw.backend.domain.model.entity.ForumComment;
import com.example.fortlomisw.backend.domain.model.entity.Person;
import com.example.fortlomisw.backend.domain.persistence.ForumCommentRepository;
import com.example.fortlomisw.backend.domain.persistence.ForumRepository;
import com.example.fortlomisw.backend.domain.persistence.UserRepository;
import com.example.fortlomisw.backend.domain.service.ForumCommentService;
import com.example.fortlomisw.shared.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import javax.validation.Validator;
import java.util.List;

@Service
public class ForumCommentServiceImpl implements ForumCommentService {


    private static final String ENTITY = "ForumComment";

    private final ForumCommentRepository forumcommentRepository;
    private final UserRepository userRepository;
    private final ForumRepository forumRepository;

    private final Validator validator;

    public ForumCommentServiceImpl(ForumCommentRepository forumcommentRepository, UserRepository userRepository, ForumRepository forumRepository, Validator validator) {
        this.forumcommentRepository = forumcommentRepository;
        this.userRepository = userRepository;
        this.forumRepository = forumRepository;
        this.validator = validator;
    }


    @Override
    public List<ForumComment> getAll() {
        return forumcommentRepository.findAll();
    }

    @Override
    public Page<ForumComment> getAll(Pageable pageable) {
        return forumcommentRepository.findAll(pageable);
    }

    @Override
    public ForumComment getById(Long forumcommentId) {
        return forumcommentRepository.findById(forumcommentId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, forumcommentId));
    }

    @Override
    public ForumComment create(Long userId, Long forumId, ForumComment request) {
        Person user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Person", userId));
        return forumRepository.findById(forumId)
                .map(forums -> {
                    request.setForum(forums);
                    request.setPerson(user);
                    return forumcommentRepository.save(request);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Forum", forumId));
    }

    @Override
    public ForumComment update(Long forumcommentId, ForumComment request) {
        return null;
    }

    @Override
    public List<ForumComment> getForumCommentByForumId(Long forumId) {
        return forumcommentRepository.findByForumId(forumId);
    }

    @Override
    public ResponseEntity<?> delete(Long forumcommentId) {
        return forumcommentRepository.findById(forumcommentId).map(forumComment -> {
            forumcommentRepository.delete(forumComment);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, forumcommentId));
    }




}
