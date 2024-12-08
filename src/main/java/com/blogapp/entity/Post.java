package com.blogapp.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "post")
public class Post {
    @Id
    private String postId;

    @Column(name="post_title", nullable = false, length = 125)
    private String postTitle;

    @Column(name="post_description", nullable = false, length = 1000)
    private String postDescription;

    @Column(name="post_content", nullable = false)
    private String postContent;
}
