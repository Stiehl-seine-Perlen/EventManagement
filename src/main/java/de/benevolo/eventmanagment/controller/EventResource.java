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
    @Produces(MediaType.APPLICATION_JSON)
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GET
    @Path("{eventId}")
    @Produces(MediaType.APPLICATION_JSON)

    public Event getEventById(@PathParam("eventId") Long id) {
        return eventService.getEventById(id);
    }

    @GET
    @Path("association/{associationId}")
    @Produces(MediaType.APPLICATION_JSON)

    public List<Event> getAllEventsForAssociation(@PathParam("associationId") Long associationId) {
        return eventService.getAllEventsOfAssociation(associationId);
    }

    @GET
    @Path("me")
    @Produces(MediaType.APPLICATION_JSON)

    public String getMe(){
        return idToken.getRawToken();
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    public Event createEvent(Event event) {
        return eventService.persistEvent(event);
    }

    /*
        Only for Authorization Testings
    */

    //only Admin
    @Path("admin")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Event> getAllEventsAdmin() {
        return eventService.getAllEvents();
    }

    //only logged in User
    @Path("user")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Event> getAllEventsUser() {
        return eventService.getAllEvents();
    }


}
