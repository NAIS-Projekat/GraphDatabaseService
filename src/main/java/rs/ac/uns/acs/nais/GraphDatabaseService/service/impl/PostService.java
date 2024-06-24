package rs.ac.uns.acs.nais.GraphDatabaseService.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.PostDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Customer;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Product;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Volunteer;
import rs.ac.uns.acs.nais.GraphDatabaseService.repository.PostRepository;
import rs.ac.uns.acs.nais.GraphDatabaseService.repository.VolunteerRepository;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.IPostService;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Post;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostService implements IPostService {

    private final PostRepository postRepository;
    private final VolunteerRepository volunteerRepository;

    @Autowired
    public PostService(PostRepository postRepository, VolunteerRepository volunteerRepository) {
        this.postRepository = postRepository;
        this.volunteerRepository = volunteerRepository;
    }

    public Volunteer createPost(PostDTO postDto) {
          Volunteer volunteer = volunteerRepository.findByEmail(postDto.getVolunteerEmail());
          Post post = new Post();
          //post.setId(postDto.getId());
          post.setContent(postDto.getContent());
          post.setTimestamp(postDto.getTimestamp());
          volunteer.addNewPost(post);
          return volunteerRepository.save(volunteer);
//        Post post = new Post();
//        post.setId(postDto.getId());
//        post.setContent(postDto.getContent());
//        post.setTimestamp(postDto.getTimestamp());
//        return postRepository.save(post);
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


//    Optional<Product> product = productRepository.findById(id);
//        if(product.isPresent()){
//        product.get().setAvailable(false);
//        productRepository.save(product.get());
//        return true;
//    }
//        return false;
    @Override
    public boolean deletePost(Long postId) {
        Optional<Post> post = postRepository.findById(postId);
        if(post.isPresent()){
            postRepository.deleteById(post.get().getId());
            return true;
        }
        return false;
    }
    @Override
    public boolean updatePost(Long id, String content) {
        Optional<Post> post = postRepository.findById(id);
        if(post.isPresent()){
            post.get().setContent(content);
            postRepository.save(post.get());
            return true;
        }
        return false;
    }

}
