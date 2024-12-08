package com.blogapp.repository;

import com.blogapp.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface PostRepository  extends JpaRepository<Post, String> {




    @Query(value = "DELETE FROM post  p WHERE p.post_id = :postId", nativeQuery = true)
    void deleteByStudyId(@Param("postId") String postId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE post p SET p.post_title = :postTitle, p.post_description = :postDescription, p.post_content = :postContent WHERE p.post_id = :postId",nativeQuery = true)
    void updatePostDetails(
            @Param("postId") String postId,
            @Param("postTitle") String postTitle,
            @Param("postDescription") String postDescription,
            @Param("postContent") String postContent );





}
