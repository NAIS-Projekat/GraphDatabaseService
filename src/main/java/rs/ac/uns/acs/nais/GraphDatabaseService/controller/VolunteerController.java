package rs.ac.uns.acs.nais.GraphDatabaseService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.PostDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Post;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Product;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Volunteer;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.impl.PostService;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.impl.VolunteerService;

import java.util.List;

@RestController
@RequestMapping("/volunteers.json")
public class VolunteerController {
    private final VolunteerService volunteerService;
    @Autowired
    public VolunteerController(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
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
}
