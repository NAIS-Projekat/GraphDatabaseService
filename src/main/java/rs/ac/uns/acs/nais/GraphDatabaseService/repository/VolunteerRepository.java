package rs.ac.uns.acs.nais.GraphDatabaseService.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Volunteer;

public interface VolunteerRepository extends Neo4jRepository<Volunteer, Long> {
    Volunteer findByEmail(String email);
}
