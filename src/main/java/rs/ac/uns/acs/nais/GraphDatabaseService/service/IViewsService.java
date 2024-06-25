package rs.ac.uns.acs.nais.GraphDatabaseService.service;

import rs.ac.uns.acs.nais.GraphDatabaseService.model.Views;

public interface IViewsService {
    boolean updateViewRelation(Long volunteerId, Long postId);
}
