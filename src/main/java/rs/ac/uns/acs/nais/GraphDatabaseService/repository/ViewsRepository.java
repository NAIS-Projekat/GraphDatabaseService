package rs.ac.uns.acs.nais.GraphDatabaseService.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Views;

public interface ViewsRepository extends Neo4jRepository<Views, Long> {
    @Query("MATCH (v:Volunteer)-[r:VIEWS]->(p:Post) " +
            "WHERE id(v) = $volunteerId AND id(p) = $postId " +
            "SET r.liked = true")
    Views updateViewRelation(Long volunteerId, Long postId);
}
