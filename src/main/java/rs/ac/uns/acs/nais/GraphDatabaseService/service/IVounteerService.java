package rs.ac.uns.acs.nais.GraphDatabaseService.service;

import rs.ac.uns.acs.nais.GraphDatabaseService.dto.PostDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.VolunteerDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Post;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Volunteer;

import java.util.List;

public interface IVounteerService {

    List<Volunteer> getAllVolunteers();
    Volunteer createVolunteer(Volunteer volunteer);
    boolean updateVolunteer(VolunteerDTO volunteer);
    boolean deleteVolunteer(Long volunteerId);
    void addView(Long volunteerId, Long postId, boolean liked);
}
