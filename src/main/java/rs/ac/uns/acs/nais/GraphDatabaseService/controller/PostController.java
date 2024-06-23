package rs.ac.uns.acs.nais.GraphDatabaseService.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.PostDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Post;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.impl.PostService;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/posts.json")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody PostDTO post) {
        Post newPost = postService.createPost(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPost);
    }

    // Endpoint to get all posts
    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }
}
