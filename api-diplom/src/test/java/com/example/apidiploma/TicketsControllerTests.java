package com.example.apidiploma;

import com.example.apidiploma.entity.City;
import com.example.apidiploma.util.Data;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.restassured.RestAssured.with;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;

public class TicketsControllerTests {


    private static final String BASEURI = "http://localhost:8080";

    private RequestSpecification spec = with()
            .baseUri(BASEURI)
            .basePath("/")
            .contentType(ContentType.JSON)
            .log().all();

    @Test
    void ticketsControllerGetAllTest() {

        spec.get("tickets/getAll")
                .then()
                .statusCode(200)
                .body("RouteOne.size", is(1),
                        "RouteTwo.size", is(1),
                        "RouteThree.size", is(1),
                        "RouteOne.price[0]", is("1000"),
                        "RouteTwo.price[0]", is("999"),
                        "RouteThree.price[0]", is("666"));
        String response = spec.get("tickets/getAll")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();
    }

    @Test
    void ticketsControllerDeparturesTest() {
        City[][] cityInfosDep = spec.get("tickets/getAllDepartures")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .as(City[][].class);

        Assertions.assertEquals(3, cityInfosDep.length, "Size of array is wrong");

        List<String> list1 = Stream.of(cityInfosDep)
                .flatMap(Arrays::stream)
                .filter(cityInfo -> cityInfo.getAirportName()
                        .equals("MinskAirport") && cityInfo.getCityName().equals("Minsk"))
                .peek(cityInfo -> System.out.println(cityInfo.getAirportName()))
                .peek(cityInfo -> System.out.println(cityInfo.getCityName()))
                .map(cityInfo -> toString())
                .collect(Collectors.toList());

        List<String> list2 = Stream.of(cityInfosDep)
                .flatMap(Arrays::stream)
                .filter(cityInfo -> cityInfo.getAirportName()
                        .equals("IzhevskAirPort") && cityInfo.getCityName().equals("Izhevsk"))
                .peek(cityInfo -> System.out.println(cityInfo.getAirportName()))
                .peek(cityInfo -> System.out.println(cityInfo.getCityName()))
                .map(cityInfo -> toString())
                .collect(Collectors.toList());

        assertThat(list1).hasSize(3);
        assertThat(list2).hasSize(3);
    }

    @Test
    void ticketsControllerArrivalsTest() {
        City[][] cityInfosArr = spec.get("tickets/getAllArrivals")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .as(City[][].class);

        spec.get("tickets/getAllArrivals")
                .then()
                .statusCode(200)
                .body(
                        "[0][0].city_name", is("Moscow"),
                        "[0][0].airport_name", is("Domodedovo"),
                        "[0][0].date", is(1611522000000L),
                        "[0][1].city_name", is("SaintP"),
                        "[0][1].airport_name", is("Pulkovo"),
                        "[0][1].date", is(1611522000000L)
                );

        System.out.println(Arrays.stream(cityInfosArr).findFirst());

        Assertions.assertEquals(3, cityInfosArr.length, "Size of array is wrong");

        System.out.println(Arrays.deepToString(cityInfosArr));
    }

    @Test
    void addTicketInTicketsStorage() {
        Data data = new Data();
        JSONObject body = data.getJsonObject();
        String response = spec
                .body(body.toString())
                .post("tickets/addNewTicket")
                .then().statusCode(200)
                .extract()
                .asString();
        System.out.println(response);
        spec
                .body(body.toString())
                .post("tickets/addNewTicket")
                .then().statusCode(200)
                .body("RouteFourth.size", is(1),
                        "RouteFourth.price[0]", is("1000"),
                        "RouteFourth.departure.size", is(1),
                        "RouteFourth.arrival.size", is(1),
                        "RouteFourth.counts[0]", is(2),
                        "RouteFourth.departure[0].city_name[0]", is("Test1"),
                        "RouteFourth.arrival[0].city_name[0]", is("Test2"));

    }

    @Test
    void addEmptyBody() {
        spec
                .post("tickets/addNewTicket")
                .then()
                .statusCode(400)
                .body("error", is("Bad Request"));
    }

    @Test
    void getTicketsByFilteredPriceTest() {
        int minPrice = 400;
        int maxPrice = 999;
        spec
                .queryParam("minPrice", minPrice)
                .queryParam("maxPrice", maxPrice)
                .get("tickets/filterByPrice").then()
                .statusCode(200)
                .body("size", is(2),
                        "[0].price", is("999"),
                        "[1].price", is("666"));
    }
}


