package rs.ac.uns.acs.nais.GraphDatabaseService.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.VolunteerDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Post;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Views;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Volunteer;
import rs.ac.uns.acs.nais.GraphDatabaseService.repository.PostRepository;
import rs.ac.uns.acs.nais.GraphDatabaseService.repository.VolunteerRepository;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.IVounteerService;

import java.util.List;
import java.util.Optional;

@Service
public class VolunteerService implements IVounteerService {
    @Autowired
    private VolunteerRepository volunteerRepository;
    @Autowired
    private PostRepository postRepository;

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
    public void addView(Long volunteerId, Long postId, boolean liked) {
        Volunteer volunteer = volunteerRepository.findById(volunteerId).orElseThrow();
        Post post = postRepository.findById(postId).orElseThrow();
        Views view = new Views();
        view.setPost(post);
        view.setLiked(liked);
        volunteer.getViews().add(view);
        volunteerRepository.save(volunteer);
    }

}
