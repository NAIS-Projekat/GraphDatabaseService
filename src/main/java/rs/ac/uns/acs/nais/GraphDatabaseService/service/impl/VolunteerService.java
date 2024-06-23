package rs.ac.uns.acs.nais.GraphDatabaseService.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Volunteer;
import rs.ac.uns.acs.nais.GraphDatabaseService.repository.VolunteerRepository;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.IVounteerService;

@Service
public class VolunteerService implements IVounteerService {
    @Autowired
    private VolunteerRepository volunteerRepository;
    @Override
    public boolean deletePostFromVolunteer(Long volunteerId, Long postId) {

        Volunteer volunteer = volunteerRepository.findById(volunteerId).orElseThrow(() -> new RuntimeException("Volunteer not found"));
        if(volunteer!=null){
            volunteer.deletePost(postId);
            volunteerRepository.save(volunteer);
            return true;
        }
        return false;

    }
}
