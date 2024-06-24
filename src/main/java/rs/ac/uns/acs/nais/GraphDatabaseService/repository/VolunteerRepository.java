package rs.ac.uns.acs.nais.GraphDatabaseService.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Volunteer;
@Repository
public interface VolunteerRepository extends Neo4jRepository<Volunteer, Long> {
    Volunteer findByEmail(String email);
}
