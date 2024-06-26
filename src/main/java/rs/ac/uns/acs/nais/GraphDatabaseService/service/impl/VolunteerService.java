package rs.ac.uns.acs.nais.GraphDatabaseService.service.impl;

import org.neo4j.driver.types.MapAccessor;
import org.neo4j.driver.types.TypeSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.data.neo4j.core.mapping.Neo4jMappingContext;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.PopularVolunteerDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.VolunteerDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Follows;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Post;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Views;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Volunteer;
import rs.ac.uns.acs.nais.GraphDatabaseService.repository.PostRepository;
import rs.ac.uns.acs.nais.GraphDatabaseService.repository.VolunteerRepository;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.IVounteerService;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

@Service
public class VolunteerService implements IVounteerService {
    @Autowired
    private VolunteerRepository volunteerRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private Neo4jClient neo4jClient;

    @Autowired
    private Neo4jMappingContext neo4jMappingContext;

    @Override
    public List<Volunteer> getAllVolunteers() {
        return volunteerRepository.findAll();
    }

    @Override
    public Volunteer createVolunteer(Volunteer volunteer) {
        return volunteerRepository.save(volunteer);
    }

    @Override
    public boolean updateVolunteer(VolunteerDTO newVolunteer) {
        Optional<Volunteer> volunteer = volunteerRepository.findById(newVolunteer.getId());
        if(volunteer.isPresent()){
            volunteer.get().setAccountPrivacy(newVolunteer.isAccountPrivacy());
            volunteer.get().setEmail(newVolunteer.getEmail());
            volunteerRepository.save(volunteer.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteVolunteer(Long volunteerId) {
        Optional<Volunteer> volunteer = volunteerRepository.findById(volunteerId);
        if(volunteer.isPresent()){
            volunteer.get().setActive(false);
            volunteerRepository.save(volunteer.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean followVolunteer(Long followerId, Long followedId) {
        Volunteer follower = volunteerRepository.findById(followerId).orElseThrow(() -> new RuntimeException("Follower not found"));
        Volunteer followed = volunteerRepository.findById(followedId).orElseThrow(() -> new RuntimeException("Followed not found"));
        Follows follows = new Follows();
        follows.setFollowed(followed);
        follows.setFollowDate(LocalDateTime.now());
        follows.setMute(false);

        try {
            follower.getFollows().add(follows);
            volunteerRepository.save(follower);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Follows> getAllFollows(Long volunteerId) {
        Volunteer volunteer = volunteerRepository.findById(volunteerId)
                .orElseThrow(() -> new RuntimeException("Volunteer not found"));

        return volunteer.getFollows();
    }
    public boolean updateMuteStatus(Long followerId, Long followedId, boolean mute) {
        return volunteerRepository.muteFollowRelationship(followerId, followedId, mute);
    }

    @Override
    public boolean unfollowVolunteer(Long followerId, Long followedId) {
        Volunteer follower = volunteerRepository.findById(followerId)
                .orElseThrow(() -> new RuntimeException("Follower not found"));

        List<Follows> followsList = follower.getFollows();

        Iterator<Follows> iterator = followsList.iterator();
        while (iterator.hasNext()) {
            Follows follows = iterator.next();
            if (follows.getFollowed().getId().equals(followedId)) {
                iterator.remove();
                volunteerRepository.save(follower);
                return true; // Successfully unfollowed
            }
        }

        return false; // Follow relationship not found
    }
    public List<Volunteer> getRecommendedVolunteers(Long volunteerId) {
        return volunteerRepository.findRecommendedVolunteers(volunteerId);
    }

    public Collection<PopularVolunteerDTO> getMostPopularVolunteers() {
        return neo4jClient.query("""
                MATCH (v:Volunteer)-[:POSTS]->(p:Post)<-[r:VIEWS]-(viewer:Volunteer)
                WITH v, p, COUNT(r) AS numViews, SUM(CASE WHEN r.liked THEN 1 ELSE 0 END) AS numLikes
                WITH v, COUNT(p) AS numPosts, SUM(numViews) AS totalViews, SUM(numLikes) AS totalLikes
                RETURN v.email AS volunteerEmail, numPosts, totalViews, totalLikes
                ORDER BY totalViews DESC
                LIMIT 5
                """)
                .fetch().all()
                .stream()
                .map(record -> mapToDTO(record))
                .collect(Collectors.toList());
    }
    public List<Volunteer> getTopVolunteersByMessageSent() {
        return volunteerRepository.getTopVolunteersByMessageSent();
    }

    private PopularVolunteerDTO mapToDTO(Map<String, Object> record) {
        String volunteerEmail = (String) record.get("volunteerEmail");
        Long numPosts = ((Number) record.get("numPosts")).longValue();
        Long totalViews = ((Number) record.get("totalViews")).longValue();
        Long totalLikes = ((Number) record.get("totalLikes")).longValue();
        return new PopularVolunteerDTO(volunteerEmail, numPosts, totalViews, totalLikes);
    }

}
