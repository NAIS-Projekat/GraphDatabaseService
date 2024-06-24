package rs.ac.uns.acs.nais.GraphDatabaseService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.MessageDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.PostDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Message;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Post;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Volunteer;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.impl.MessageService;

import java.util.List;

@RestController
@RequestMapping("/messages.json")
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public ResponseEntity<Message> createMessage(@RequestBody MessageDTO messageDTO) {
        Message message = messageService.createMessage(messageDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }
    @PutMapping
    public ResponseEntity<Message> updateMessage(@RequestParam("id") Long id, @RequestParam("messageContent") String content){
        if(messageService.updateMessage(id, content)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("markAsDelivered")
    public ResponseEntity<Message> markAsDelivered(@RequestParam("id") Long id){
        if(messageService.markAsDelivered(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("markAsRead")
    public ResponseEntity<Message> markAsRead(@RequestParam("id") Long id){
        if(messageService.markAsRead(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping
    public ResponseEntity<Message> deleteMessage(@RequestParam("messageId") Long messageId){
        if(messageService.deleteMessage(messageId)){
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/sent/{userId}")
    public ResponseEntity<List<Message>> getSentMessages(@PathVariable Long userId) {
        List<Message> sentMessages = messageService.getSentMessages(userId);
        return ResponseEntity.ok(sentMessages);
    }

    @GetMapping("/received/{userId}")
    public ResponseEntity<List<Message>> getReceivedMessages(@PathVariable Long userId) {
        List<Message> receivedMessages = messageService.getReceivedMessages(userId);
        return ResponseEntity.ok(receivedMessages);
    }
}
