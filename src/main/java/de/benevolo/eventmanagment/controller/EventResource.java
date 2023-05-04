package de.benevolo.eventmanagment.controller;


import de.benevolo.entities.events.Event;
import de.benevolo.eventmanagment.services.EventService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/event")
public class EventResource {

    @Inject
    EventService eventService;

    @GET
    @Path("{eventId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Event getEventById(@PathParam("eventId") Long id){
        return eventService.getEventById(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Event> getAllEvents(){
        return eventService.getAllEvents();
    }

    @GET
    @Path("{associationId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Event> getAllEventsForAssociation(@PathParam("associationId") Long associationId){
        return eventService.getAllEventsOfAssociation(associationId);
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Event createEvent(Event event){
        return eventService.persistEvent(event);
    }

}
