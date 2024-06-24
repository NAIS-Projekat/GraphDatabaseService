package rs.ac.uns.acs.nais.GraphDatabaseService.service;

import rs.ac.uns.acs.nais.GraphDatabaseService.dto.PostDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Post;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Volunteer;

import java.util.List;

public interface IPostService {

    Volunteer createPost(PostDTO post);
    List<Post> getAllPosts();
    boolean deletePost(Long postId);
    public boolean updatePost(Long id, String postName);
}
