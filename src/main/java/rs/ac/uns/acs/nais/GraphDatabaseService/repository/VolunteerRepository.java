package rs.ac.uns.acs.nais.GraphDatabaseService.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.GraphDatabaseService.dto.PopularVolunteerDTO;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Volunteer;

import java.util.Collection;
import java.util.List;

@Repository
public interface VolunteerRepository extends Neo4jRepository<Volunteer, Long> {
    Volunteer findByEmail(String email);
    @Query("MATCH (follower:Volunteer)-[r:FOLLOWS]->(followed:Volunteer) " +
            "WHERE id(follower) = $followerId AND id(followed) = $followedId " +
            "SET r.mute = $mute " +
            "RETURN count(r) > 0")
    boolean muteFollowRelationship(Long followerId, Long followedId, boolean mute);

    @Query("""
    MATCH (v:Volunteer)<-[:FOLLOWS]-(follower:Volunteer)
    WHERE id(v) = $volunteerId AND NOT (v)-[:FOLLOWS]->(follower)
    WITH COLLECT(DISTINCT follower) AS followersNotFollowedBack

    MATCH (v:Volunteer)-[:FOLLOWS]->(following:Volunteer)-[:FOLLOWS]->(recommendation:Volunteer)
    WHERE id(v) = $volunteerId AND NOT (v)-[:FOLLOWS]->(recommendation)
    WITH followersNotFollowedBack, COLLECT(DISTINCT recommendation) AS recommendationsByFollowing

    WITH followersNotFollowedBack + recommendationsByFollowing AS combinedRecommendations
    UNWIND combinedRecommendations AS recommendation
    RETURN DISTINCT recommendation
    LIMIT 10
    """)
    List<Volunteer> findRecommendedVolunteers(Long volunteerId);
    @Query("""
    MATCH (v:Volunteer)-[:SENDS]->(m:Message)
    WITH v, COUNT(m) AS numMess
    RETURN v
    ORDER BY numMess DESC
    LIMIT 3
    """)
    List<Volunteer> getTopVolunteersByMessageSent();
}
