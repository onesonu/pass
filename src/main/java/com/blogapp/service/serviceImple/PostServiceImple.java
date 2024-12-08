package com.blogapp.service.serviceImple;

import com.blogapp.entity.Post;
import com.blogapp.payload.PostDto;
import com.blogapp.repository.PostRepository;
import com.blogapp.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class PostServiceImple implements PostService {

    @Autowired private PostRepository postRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PostDto createPost(PostDto postDto) {
        String postID = UUID.randomUUID().toString();

        Post pst = new Post();
        pst.setPostId(postID);
        pst.setPostTitle(postDto.getPostTitle());
        pst.setPostDescription(postDto.getPostDescription());
        pst.setPostContent(postDto.getPostContent());

        postRepo.save(pst);
        return mapTopostDto(pst);

    }
    @Override
    public PostDto readPost(String post_id) {
       Optional<Post> post = postRepo.findById(post_id);
       if (post.isPresent()){
          Post get_post =  post.get();
          return mapTopostDto(get_post);

       }else {
           return null;
       }
    }

    @Override
    public String updatePost(PostDto postDto , String  post_id) {
        Optional<Post> findpost = postRepo.findById(post_id);
        if (findpost.isPresent()){
            String pst_title = postDto.getPostTitle();
            String pst_des = postDto.getPostDescription();
            String pst_con = postDto.getPostContent();
            postRepo.updatePostDetails(post_id,pst_title,pst_des,pst_con);
            return "update Successfully";
        }
        else{
            return "no post found in this post_id";
        }
    }

    @Override
    public String deletePost(String post_id) {
        Optional<Post> finded_post = postRepo.findById(post_id);
        postRepo.deleteById(post_id);
       return "deleted successfully";
    }

    @Override
    public List<PostDto> getDataByShorting(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sorted  = sortDir.equalsIgnoreCase(Sort.Direction.DESC.name())?
                Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo,pageSize,sorted);
       Page<Post> allpost =  postRepo.findAll(pageable);
       List<Post> post = allpost.getContent();
                 List<PostDto> postDtos = post.stream().map(p-> mapTopostDto(p)).collect(Collectors.toList());
        return postDtos;
    }
    

    //map to PostEntity
    public Post mapToPost(PostDto postDto){
        return modelMapper.map(postDto, Post.class);
    }
    //map to PostDto
    public PostDto mapTopostDto(Post post){
        return modelMapper.map(post,PostDto.class);
    }

}
