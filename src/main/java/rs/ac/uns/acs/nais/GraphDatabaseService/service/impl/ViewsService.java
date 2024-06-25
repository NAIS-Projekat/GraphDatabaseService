package rs.ac.uns.acs.nais.GraphDatabaseService.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.acs.nais.GraphDatabaseService.model.Views;
import rs.ac.uns.acs.nais.GraphDatabaseService.repository.ViewsRepository;
import rs.ac.uns.acs.nais.GraphDatabaseService.service.IViewsService;

@Service
public class ViewsService implements IViewsService {
    private final ViewsRepository viewsRepository;

    @Autowired
    public ViewsService(ViewsRepository viewsRepository) {
        this.viewsRepository = viewsRepository;
    }

    public boolean updateViewRelation(Long volunteerId, Long postId) {
        try {
            viewsRepository.updateViewRelation(volunteerId, postId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
