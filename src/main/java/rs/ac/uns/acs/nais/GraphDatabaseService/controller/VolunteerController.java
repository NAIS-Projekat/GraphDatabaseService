package rs.ac.uns.acs.nais.GraphDatabaseService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Product;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Volunteer;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.impl.PostService;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.impl.VolunteerService;

@RestController
@RequestMapping("/volunteers.json")
public class VolunteerController {
    private final VolunteerService volunteerService;
    @Autowired
    public VolunteerController(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    @DeleteMapping("deletePost")
    public ResponseEntity<Volunteer> deletePost(@RequestParam("volunteerId") Long volunteerId, @RequestParam("postId") Long postId){
        if(volunteerService.deletePostFromVolunteer(volunteerId, postId)){
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
//    @DeleteMapping
//    public ResponseEntity<Product> deleteProduct(@RequestParam("id") String id){
//        if(productService.deleteProduct(id)){
//            return new ResponseEntity<>(HttpStatus.OK);
//        }
//
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
}
