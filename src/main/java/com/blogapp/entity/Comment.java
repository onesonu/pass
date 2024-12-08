package com.blogapp.entity;


import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long comment_id;

    @Column(name = "cmnt_name", nullable = false, length = 125)
    private String name;

    @Column(name="cmnt_email", nullable = false, length = 125)
    private String email;

    @Column(name="cmnt_content", nullable = false, length = 125)
    private String content;

}
