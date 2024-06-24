package rs.ac.uns.acs.nais.GraphDatabaseService.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.PostDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Post;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Volunteer;
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

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }
    @PostMapping
    public ResponseEntity<Volunteer> createPost(@RequestBody PostDTO post) {
        Volunteer volunteer = postService.createPost(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(volunteer);
    }

    @DeleteMapping
    public ResponseEntity<Post> deletePost(@RequestParam("postId") Long postId){
        if(postService.deletePost(postId)){
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping
    public ResponseEntity<Post> updatePost(@RequestParam("id") Long id, @RequestParam("postContent") String content){
        if(postService.updatePost(id, content)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
