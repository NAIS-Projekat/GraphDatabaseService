package rs.ac.uns.acs.nais.GraphDatabaseService.service;

import rs.ac.uns.acs.nais.GraphDatabaseService.dto.MessageDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.PostDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Message;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Post;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Volunteer;

import java.util.List;

public interface IMessageService {
    List<Message> getAllMessages();
    Message createMessage(MessageDTO messageDTO);
    boolean updateMessage(Long id, String messageContent);
    boolean deleteMessage(Long messageId);
    boolean markAsDelivered(Long id);
    boolean markAsRead(Long id);
    List<Message> getSentMessages(Long userId);
    List<Message> getReceivedMessages(Long userId);
}
