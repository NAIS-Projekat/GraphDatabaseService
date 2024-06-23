package rs.ac.uns.acs.nais.GraphDatabaseService.service;

import rs.ac.uns.acs.nais.GraphDatabaseService.dto.PostDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Post;

import java.util.List;

public interface IPostService {

    Post createPost(PostDTO post);
    List<Post> getAllPosts();
}
