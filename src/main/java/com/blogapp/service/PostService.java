package com.blogapp.service;

import com.blogapp.entity.Post;
import com.blogapp.payload.PostDto;

import java.util.List;
import java.util.Optional;

public interface PostService {
    public PostDto createPost(PostDto postDto);
    public PostDto readPost(String  post_id);
    public String  updatePost(PostDto postDto, String post_id);
    public String deletePost(String post_id);
    public List<PostDto> getDataByShorting(int pageNo, int pageSize, String sortBy, String sortDir);


}
