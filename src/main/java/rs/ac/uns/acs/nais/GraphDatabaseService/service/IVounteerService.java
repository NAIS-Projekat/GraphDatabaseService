package rs.ac.uns.acs.nais.GraphDatabaseService.service;

import rs.ac.uns.acs.nais.GraphDatabaseService.dto.PopularVolunteerDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.PostDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.VolunteerDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Follows;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Post;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Volunteer;

import java.util.Collection;
import java.util.List;

public interface IVounteerService {

    List<Volunteer> getAllVolunteers();
    Volunteer createVolunteer(Volunteer volunteer);
    boolean updateVolunteer(VolunteerDTO volunteer);
    boolean deleteVolunteer(Long volunteerId);
    boolean followVolunteer(Long followerId, Long followedId);
    List<Follows> getAllFollows(Long volunteerId);
    boolean updateMuteStatus(Long followerId, Long followedId, boolean mute);
    boolean unfollowVolunteer(Long followerId, Long followedId);
    List<Volunteer> getRecommendedVolunteers(Long volunteerId);
    Collection<PopularVolunteerDTO> getMostPopularVolunteers();
    List<Volunteer> getTopVolunteersByMessageSent();
}
