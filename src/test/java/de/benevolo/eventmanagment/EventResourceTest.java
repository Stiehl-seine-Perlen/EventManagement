package de.benevolo.eventmanagment;

import de.benevolo.eventmanagment.repositories.EventRepository;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
//@TestHTTPEndpoint(EventResource.class)
public class EventResourceTest {

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

    private String responseEvent1andEvent2 = """
            [
              {
                "eventId": 1,
                "ownedByAssociationId": 4,
                "eventName": "Erstes Event",
                "eventDescription": "Erstes Event von Association 4",
                "membersOnly": true,
                "eventParticipants": []
              },
              {
                "eventId": 2,
                "ownedByAssociationId": 5,
                "eventName": "Zweites Event",
                "eventDescription": "Event 2 von Association 5",
                "membersOnly": true,
                "eventParticipants": []
              }
            ]
            """;



    @Inject
    EventRepository eventRepository;

    @Transactional
    @BeforeEach
    public void emptyTheDatabase() {
        eventRepository.deleteAll();
    }

    @Test
    public void shouldReturnEmptyList() {
        when()
                .get("/event")
                .then()
                .statusCode(200)
                .body(is("[]"));
    }


    @Test
    public void shouldPersistTwoEvents() {



        given()
                .body(event1)
                .contentType(ContentType.JSON)
                .when()
                .post("/event")
                .then()
                .statusCode(200);

                given()
                .body(event2)
                .contentType(ContentType.JSON)
                .when()
                .post("/event")
                .then()
                .statusCode(200);


    }

}
