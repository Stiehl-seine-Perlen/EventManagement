package de.benevolo.eventmanagment;

import de.benevolo.entities.events.Event;
import de.benevolo.eventmanagment.repositories.EventRepository;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

@QuarkusTest
//@TestHTTPEndpoint(EventResource.class)
public class EventResourceTest {


    @Inject EventRepository eventRepository;

    private String event1 = """
                  {
                  "ownedByAssociationId": 4,
                  "eventName": "Erstes Event",
                  "eventDescription": "Erstes Event von Association 4",
                  "membersOnly": true,
                  "eventParticipants":[]
                }
                				""";

    private String event2 = """
                  {
                  "ownedByAssociationId": 5,
                  "eventName": "Zweites Event",
                  "eventDescription": "Event 2 von Association 5",
                  "membersOnly": true,
                  "eventParticipants":[]
                }
                				""";




    @BeforeEach
    @Transactional
    public void emptyTheDatabase() {
        eventRepository.deleteAll();
    }

    @Test
    public void shouldReturnEmptyList() {
        when()
                .get("/all")
                .then()
                .statusCode(200)
                .body(is("[]"));
    }


    @Test
    public void shouldPersistTwoEvents() {

        persistTwoEvents();

        //then
        List<Event> list = eventRepository.listAll();
        assertThat(list, hasSize(2));

    }

/*    @Test
    public void shouldFetchEventByAssociationId(){

        persistTwoEvents();


    }

    @Test
    public void shouldFetchEventByEventId(){

        persistTwoEvents();
    }*/

    public void persistTwoEvents(){
                given()
                .body(event1)
                .contentType(ContentType.JSON)
                .when()
                .post("/")
                .then()
                .statusCode(200);

                given()
                .body(event2)
                .contentType(ContentType.JSON)
                .when()
                .post("/")
                .then()
                .statusCode(200);
    }
}
