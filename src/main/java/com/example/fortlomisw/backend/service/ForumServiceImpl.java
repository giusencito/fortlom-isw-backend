package com.example.fortlomisw.backend.service;

import com.example.fortlomisw.backend.domain.model.entity.Forum;
import com.example.fortlomisw.backend.domain.persistence.ForumRepository;
import com.example.fortlomisw.backend.domain.persistence.UserRepository;
import com.example.fortlomisw.backend.domain.service.ForumService;
import com.example.fortlomisw.shared.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import javax.validation.Validator;
import java.util.List;

@Service
public class ForumServiceImpl implements ForumService {





    private static final String ENTITY = "Forum";
    private static final String ENTITY2 = "User";

    private final ForumRepository forumRepository;
    private final UserRepository userRepository;

    private final Validator validator;

    public ForumServiceImpl(ForumRepository forumRepository, UserRepository userRepository, Validator validator) {
        this.forumRepository = forumRepository;
        this.userRepository = userRepository;
        this.validator = validator;
    }


    @Override
    public List<Forum> getAllForums() {
        return forumRepository.findAll();
    }

    @Override
    public Page<Forum> getAllForums(Pageable pageable) {
        return forumRepository.findAll(pageable);
    }

    @Override
    public Forum getForumById(Long ForumId) {
        return forumRepository.findById(ForumId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, ForumId));
    }

    @Override
    public Forum createForum(Long userId, Forum request) {

        return userRepository.findById(userId)
                .map(users -> {
                    request.setPerson(users);
                    return forumRepository.save(request);
                })
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY2, userId));
    }

    @Override
    public Forum updateForum(Long forumId, Forum request) {
        return null;
    }

    @Override
    public List<Forum> getForumsByUserId(Long userId) {
        return forumRepository.findByPersonId(userId);
    }

    @Override
    public ResponseEntity<?> deleteForum(Long forumId) {
        return forumRepository.findById(forumId).map(Forum -> {
            forumRepository.delete(Forum);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, forumId));
    }
}
