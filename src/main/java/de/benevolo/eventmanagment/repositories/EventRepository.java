package de.benevolo.eventmanagment.repositories;

import de.benevolo.entities.events.Event;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import java.util.List;

public class EventRepository implements PanacheRepository<Event> {

}
