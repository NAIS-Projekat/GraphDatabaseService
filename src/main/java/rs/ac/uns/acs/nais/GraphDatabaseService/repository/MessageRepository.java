package rs.ac.uns.acs.nais.GraphDatabaseService.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Message;

import java.util.List;

@Repository
public interface MessageRepository extends Neo4jRepository<Message, Long> {
    List<Message> findBySender_Id(Long senderId);
    List<Message> findByReceiver_Id(Long receiverId);
}
