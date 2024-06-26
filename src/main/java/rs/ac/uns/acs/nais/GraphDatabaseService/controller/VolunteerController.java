package rs.ac.uns.acs.nais.GraphDatabaseService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.PopularVolunteerDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.PostDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.VolunteerDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.*;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.impl.MessageService;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.impl.PostService;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.impl.ViewsService;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.impl.VolunteerService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/volunteers.json")
public class VolunteerController {
    private final VolunteerService volunteerService;
    private final MessageService messageService;
    private final ViewsService viewsService;
    @Autowired
    public VolunteerController(VolunteerService volunteerService, MessageService messageService, ViewsService viewsService) {
        this.volunteerService = volunteerService;
        this.messageService = messageService;
        this.viewsService = viewsService;
    }

    @GetMapping
    public ResponseEntity<List<Volunteer>> getAllVolunteers() {
        List<Volunteer> volunteers = volunteerService.getAllVolunteers();
        return ResponseEntity.ok(volunteers);
    }
    @PostMapping
    public ResponseEntity<Volunteer> createVolunteer(@RequestBody Volunteer volunteer) {
        Volunteer newVolunteer = volunteerService.createVolunteer(volunteer);
        if(newVolunteer != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(newVolunteer);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(newVolunteer);
    }
    @PutMapping
    public ResponseEntity<Volunteer> updateVolunteer(@RequestBody VolunteerDTO volunteer) {

        if(volunteerService.updateVolunteer(volunteer)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping
    public ResponseEntity<Volunteer> deleteVolunteer(@RequestParam("volunteerId") Long volunteerId){
        if(volunteerService.deleteVolunteer(volunteerId)){
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("allMessages")
    public ResponseEntity<List<Message>> getAllMessages() {
        List<Message> messages = messageService.getAllMessages();
        return ResponseEntity.ok(messages);
    }
//    @PostMapping("view/{volunteerId}/{postId}")
//    public ResponseEntity<Void> addView(@PathVariable Long volunteerId, @PathVariable Long postId, @RequestParam boolean liked) {
//        volunteerService.addView(volunteerId, postId, liked);
//        return ResponseEntity.ok().build();
//    }
    @PutMapping("likes/{volunteerId}/{postId}")
    public ResponseEntity<Boolean> likePost(@PathVariable Long volunteerId, @PathVariable Long postId) {
        boolean success = viewsService.updateViewRelation(volunteerId, postId);
        if (success) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.status(500).body(false);
        }
    }
    @PutMapping("/follow/{followerId}/{followedId}")
    public ResponseEntity<String> followVolunteer(
                                    @PathVariable Long followerId,
                                    @PathVariable Long followedId) {

        boolean success = volunteerService.followVolunteer(followerId, followedId);

        if (success) {
            return ResponseEntity.ok("Successfully followed volunteer.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to follow volunteer.");
        }
    }
    @GetMapping("follows/{volunteerId}")
    public ResponseEntity<List<Follows>> getAllFollows(
            @PathVariable Long volunteerId) {

        List<Follows> follows = volunteerService.getAllFollows(volunteerId);

        return ResponseEntity.ok(follows);
    }
    @PutMapping("follows/mute/{followerId}/{followedId}")
    public ResponseEntity<Boolean> updateMuteStatus(
            @PathVariable Long followerId,
            @PathVariable Long followedId,
            @RequestParam boolean mute) {

        boolean success = volunteerService.updateMuteStatus(followerId, followedId, mute);

        if (success) {
            return ResponseEntity.ok(true); // Successfully updated mute status
        } else {
            return ResponseEntity.notFound().build(); // Follow relationship not found
        }
    }
    @DeleteMapping("unfollow/{followerId}/{followedId}")
    public ResponseEntity<Boolean> unfollowVolunteer(
            @PathVariable Long followerId,
            @PathVariable Long followedId) {

        boolean success = volunteerService.unfollowVolunteer(followerId, followedId);

        if (success) {
            return ResponseEntity.ok(true); // Successfully unfollowed
        } else {
            return ResponseEntity.notFound().build(); // Follow relationship not found
        }
    }

    @GetMapping("recommendations/{volunteerId}")
    public ResponseEntity<List<Volunteer>> getRecommendedVolunteers(@PathVariable Long volunteerId) {
        List<Volunteer> recommendedVolunteers = volunteerService.getRecommendedVolunteers(volunteerId);
        return ResponseEntity.ok(recommendedVolunteers);
    }
    @GetMapping("popular")
    public ResponseEntity<Collection<PopularVolunteerDTO>> getMostPopularVolunteers() {
        Collection<PopularVolunteerDTO> popularVolunteers = volunteerService.getMostPopularVolunteers();
        return ResponseEntity.ok(popularVolunteers);
    }
    @GetMapping("/topByMessages")
    public ResponseEntity<List<Volunteer>> getTopVolunteersByMessageSent() {
        List<Volunteer> topVolunteers = volunteerService.getTopVolunteersByMessageSent();
        return ResponseEntity.ok(topVolunteers);
    }
}
