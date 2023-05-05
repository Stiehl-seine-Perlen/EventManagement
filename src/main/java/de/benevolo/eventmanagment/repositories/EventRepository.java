package de.benevolo.eventmanagment.repositories;

import de.benevolo.entities.events.Event;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.jboss.resteasy.annotations.Query;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class EventRepository implements PanacheRepository<Event> {


    //TODO: Debug this
    public List<Event> getAllEventsOfAssociation(Long associationId) {
        return find("SELECT e FROM Event e WHERE e.ownedByAssociationId = ?1", associationId).list();
    }
}