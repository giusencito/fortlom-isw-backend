package com.example.fortlomisw.backend.resource.ForumComment;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CreateForumCommentResource {
    private String CommentDescription;

    private Date registerdate;
}
