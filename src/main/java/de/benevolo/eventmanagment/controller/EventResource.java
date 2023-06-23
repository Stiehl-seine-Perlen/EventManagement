package de.benevolo.eventmanagment.controller;


import de.benevolo.entities.events.Event;
import de.benevolo.eventmanagment.services.EventService;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/event")
public class EventResource {

    @Inject
    EventService eventService;

    @GET
    //@RolesAllowed("user")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GET
    //@RolesAllowed("user")
    @Path("{eventId}")
    @Produces(MediaType.APPLICATION_JSON)

    public Event getEventById(@PathParam("eventId") Long id) {
        return eventService.getEventById(id);
    }

    @GET
    //@RolesAllowed("user")
    @Path("association/{associationId}")
    @Produces(MediaType.APPLICATION_JSON)

    public List<Event> getAllEventsForAssociation(@PathParam("associationId") Long associationId) {
        return eventService.getAllEventsOfAssociation(associationId);
    }

    @POST
    //@RolesAllowed("admin")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    public Event createEvent(Event event) {
        return eventService.persistEvent(event);
    }

    @PUT
    @Path("update/{eventId}")
    @Transactional
    public Event updateEvent(@PathParam("eventId") Long id, Event event) {
        Event entity = eventService.getEventById(id);
        if(entity == null) {
            throw new NotFoundException();
        }

        entity.setEventName(event.getEventName());
        entity.setOwnedByAssociationId(event.getOwnedByAssociationId());
        entity.setEventDescription(event.getEventDescription());
        entity.setMembersOnly(event.getMembersOnly());
        entity.setDate(event.getDate());

        return entity;
    }

    @DELETE
    @Path("delete/{eventId}")
    @Transactional
    public void deleteEvent(@PathParam("eventId") Long id) {
        eventService.deleteEvent(id);
    }
}
