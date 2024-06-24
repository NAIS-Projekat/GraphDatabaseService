package rs.ac.uns.acs.nais.GraphDatabaseService.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.MessageDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Message;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Post;
import rs.ac.uns.acs.nais.GraphDatabaseService.repository.MessageRepository;
import rs.ac.uns.acs.nais.GraphDatabaseService.repository.VolunteerRepository;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.IMessageService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService implements IMessageService {
    private final MessageRepository messageRepository;
    private final VolunteerRepository volunteerRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository, VolunteerRepository volunteerRepository) {
        this.messageRepository = messageRepository;
        this.volunteerRepository = volunteerRepository;
    }

    @Override
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    @Override
    public Message createMessage(MessageDTO messageDTO) {
        Message message = new Message();
        message.setContent(messageDTO.getContent());
        message.setSender(volunteerRepository.findByEmail(messageDTO.getSenderEmail()));
        message.setReceiver(volunteerRepository.findByEmail(messageDTO.getReceiverEmail()));
        message.setDelivered(false);
        message.setSeen(false);
        message.setTimestamp(LocalDateTime.now());
        return messageRepository.save(message);
    }

    @Override
    public boolean updateMessage(Long id, String messageContent) {
        Optional<Message> message = messageRepository.findById(id);
        if(message.isPresent()){
            message.get().setContent(messageContent);
            messageRepository.save(message.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteMessage(Long messageId) {
        Optional<Message> message = messageRepository.findById(messageId);
        if(message.isPresent()){
            messageRepository.deleteById(message.get().getId());
            return true;
        }
        return false;
    }

    @Override
    public boolean markAsDelivered(Long id) {
        Optional<Message> message = messageRepository.findById(id);
        if(message.isPresent()){
            message.get().setDelivered(true);
            messageRepository.save(message.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean markAsRead(Long id) {
        Optional<Message> message = messageRepository.findById(id);
        if(message.isPresent()){
            message.get().setDelivered(true);
            message.get().setSeen(true);
            messageRepository.save(message.get());
            return true;
        }
        return false;
    }
    @Override
    public List<Message> getSentMessages(Long userId) {
        return messageRepository.findBySender_Id(userId);
    }
    @Override
    public List<Message> getReceivedMessages(Long userId) {
        return messageRepository.findByReceiver_Id(userId);
    }

}
