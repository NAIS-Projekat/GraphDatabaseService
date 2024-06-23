package rs.ac.uns.acs.nais.GraphDatabaseService.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.PostDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Customer;
import rs.ac.uns.acs.nais.GraphDatabaseService.repository.PostRepository;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.IPostService;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Post;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class PostService implements IPostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post createPost(PostDTO postDto) {
        Post post = new Post();
        post.setId(postDto.getId());
        post.setContent(postDto.getContent());
        post.setTimestamp(postDto.getTimestamp());
        return postRepository.save(post);
    }

//    @Override
//    public Customer addNewCustomer(Customer customer) {
//        customer.setActive(true);
//        Customer customer1 = customerRepository.save(customer);
//        return customer1;
//    }

    // Method to retrieve all posts
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

}
