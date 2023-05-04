package de.benevolo.eventmanagment.repositories;

import de.benevolo.entities.events.Event;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class EventRepository implements PanacheRepository<Event> {


    public List<Event> getAllEventsOfAssociation(Long associationId) {
        // Statement stolen from AccountRepository -> fa correct?
        return find("SELECT e FROM Event e WHERE e.ownedByAssociationId = :associationId", associationId).list();
    }
}