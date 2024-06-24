package rs.ac.uns.acs.nais.GraphDatabaseService.model;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.time.LocalDateTime;

@Node
public class Message {
    @Id @GeneratedValue
    private Long id;
    private String content;
    private boolean seen;
    private boolean delivered;
    private LocalDateTime timestamp;
    @Relationship(value="SENDS", direction=Relationship.Direction.INCOMING)
    private Volunteer sender;
    @Relationship(value="RECEIVES", direction=Relationship.Direction.INCOMING)
    private Volunteer receiver;

    public Message() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Volunteer getSender() {
        return sender;
    }

    public void setSender(Volunteer sender) {
        this.sender = sender;
    }

    public Volunteer getReceiver() {
        return receiver;
    }

    public void setReceiver(Volunteer receiver) {
        this.receiver = receiver;
    }
}
