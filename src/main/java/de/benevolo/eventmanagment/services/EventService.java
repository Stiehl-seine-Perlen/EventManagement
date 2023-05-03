package de.benevolo.eventmanagment.services;

import com.github.dockerjava.api.exception.NotAcceptableException;
import de.benevolo.entities.events.Event;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import de.benevolo.eventmanagment.repositories.EventRepository;

import java.util.List;

@ApplicationScoped
public class EventService {

   @Inject
   EventRepository eventRepository;

   public Event getEventById(Long id){
       return eventRepository.findById(id);
   }

   public List<Event> getAllEvents(){
       return eventRepository.listAll();
   }

   public List<Event> getAllEventsOfAssociation(Long associationId){
       return eventRepository.getAllEventsOfAssociation(associationId);
   }

   @Transactional
   public Event persistEvent(Event event){
       try {
           eventRepository.persist(event);
           return event;
       } catch (Exception e) {
           throw new NotAcceptableException("Could Not Persist Event with name" + event.getEventName());
       }
   }

   public Event createEvent(Event event){
       return this.persistEvent(event);
   }
}
