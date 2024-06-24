package rs.ac.uns.acs.nais.GraphDatabaseService.model;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Iterator;
import java.util.List;

@Node
public class Volunteer {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private String email;
    private boolean isActive;
    private boolean accountPrivacy;
    @Relationship(value = "POSTS", direction = Relationship.Direction.OUTGOING)
    private List<Post> posts;

    @Relationship(type = "VIEWS", direction = Relationship.Direction.OUTGOING)
    private List<Views> views;

    public Volunteer() {
    }
    public boolean isAccountPrivacy() {
        return accountPrivacy;
    }

    public void setAccountPrivacy(boolean accountPrivacy) {
        this.accountPrivacy = accountPrivacy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
    public void addNewPost(Post post){
        this.posts.add(post);
    }
    public void deletePost(Long postId){
        Iterator<Post> iterator = posts.iterator();
        while (iterator.hasNext()) {
            Post post = iterator.next();
            if (post.getId().equals(postId)) {
                iterator.remove();
                break;
            }
        }
    }
    public List<Views> getViews() {
        return views;
    }

    public void setViews(List<Views> views) {
        this.views = views;
    }
}
