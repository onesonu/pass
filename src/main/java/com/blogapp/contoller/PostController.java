package com.blogapp.contoller;

import com.blogapp.payload.PostDto;
import com.blogapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private PostService postService;
    //http://localhost:9090/api/post/createPost
    @PostMapping("/createPost")
    //create post
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        PostDto savedpostDto = postService.createPost(postDto);
        return new ResponseEntity<>(savedpostDto, HttpStatus.CREATED);
    }


    //http://localhost:9090/api/post/delete/{post_id}
    //delete post
    @DeleteMapping("/delete/{post_id}")
    public ResponseEntity<?> deletePost(@PathVariable("post_id") String post_id){
        String deletmsg  =postService.deletePost(post_id);
        return new ResponseEntity<>(deletmsg,HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updatePost(@RequestBody PostDto postDto , @RequestParam String post_id){
      String updateMsg= postService.updatePost(postDto, post_id);
       return new ResponseEntity<>(updateMsg,HttpStatus.OK);

    }
   // http://localhost:9090/api/post/post_id
    @GetMapping("/post_id")
    public ResponseEntity<?> getByPost_id(@RequestParam() String post_id){
        PostDto postDto = postService.readPost(post_id);
        return new ResponseEntity<>(postDto,HttpStatus.OK);
    }

    //http://localhost:9090/api/post/getAllBySorting?pageNo=1&pageSize=3&sortBy=postTitle&sortDir=ASC
    @GetMapping("/getAllBySorting")
    public ResponseEntity<List<PostDto>> getAllPost(
            @RequestParam(name = "pageNo",defaultValue = "1", required = false) int pageNo,
            @RequestParam(name = "pageSize",defaultValue = "3", required = false) int pageSize,
            @RequestParam(name = "sortBy",defaultValue = "title", required = false) String sortBy,
            @RequestParam(name = "sortDir",defaultValue = "ASC", required = false) String sortDir
    ){
        List<PostDto> allpost  =postService.getDataByShorting(pageNo,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(allpost,HttpStatus.OK);
    }


}
