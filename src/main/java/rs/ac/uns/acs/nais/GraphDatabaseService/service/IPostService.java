package rs.ac.uns.acs.nais.GraphDatabaseService.service;

import rs.ac.uns.acs.nais.GraphDatabaseService.dto.PostDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Post;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Views;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Volunteer;

import java.util.List;

public interface IPostService {

    Volunteer createPost(PostDTO post);
    List<Post> getAllPosts();
    boolean deletePost(Long postId);
    boolean updatePost(Long id, String content);
    void addView(Long volunteerId, Long postId, boolean liked);
    List<Views> getAllViews(Long postId);
}
