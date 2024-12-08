package com.blogapp.payload;

import com.blogapp.entity.Post;
import lombok.Data;

@Data
public class CommentDto {

    private long comment_id;

    private String name;

    private String email;

    private String content;

    private Post post;

}
