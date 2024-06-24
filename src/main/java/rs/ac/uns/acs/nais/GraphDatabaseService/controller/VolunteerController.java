package rs.ac.uns.acs.nais.GraphDatabaseService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.PostDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.VolunteerDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Message;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Post;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Product;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Volunteer;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.impl.MessageService;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.impl.PostService;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.impl.VolunteerService;

import java.util.List;

@RestController
@RequestMapping("/volunteers.json")
public class VolunteerController {
    private final VolunteerService volunteerService;
    private final MessageService messageService;
    @Autowired
    public VolunteerController(VolunteerService volunteerService, MessageService messageService) {
        this.volunteerService = volunteerService;
        this.messageService = messageService;
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
}
