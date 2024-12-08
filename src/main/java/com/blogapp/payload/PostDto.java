package com.blogapp.payload;

import lombok.Data;

@Data
public class PostDto {

    private String postId;
    private String postTitle;
    private String postDescription;
    private String postContent;
}
