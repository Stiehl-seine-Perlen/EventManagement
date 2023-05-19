package de.benevolo.eventmanagment.controller;


import de.benevolo.entities.events.Event;
import de.benevolo.eventmanagment.services.EventService;
import io.quarkus.oidc.IdToken;
import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/event")
public class EventResource {

    @Inject
    EventService eventService;

    @Inject
    @IdToken
    JsonWebToken idToken;

    @GET
    @RolesAllowed("user")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GET
    @RolesAllowed("user")
    @Path("{eventId}")
    @Produces(MediaType.APPLICATION_JSON)

    public Event getEventById(@PathParam("eventId") Long id) {
        return eventService.getEventById(id);
    }

    @GET
    @RolesAllowed("user")
    @Path("association/{associationId}")
    @Produces(MediaType.APPLICATION_JSON)

    public List<Event> getAllEventsForAssociation(@PathParam("associationId") Long associationId) {
        return eventService.getAllEventsOfAssociation(associationId);
    }

    @POST
    @RolesAllowed("admin")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    public Event createEvent(Event event) {
        return eventService.persistEvent(event);
    }


}
