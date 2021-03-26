package com.nttdata;

import io.quarkus.test.junit.QuarkusTest;
import javax.ws.rs.core.MediaType;
import org.junit.jupiter.api.Test;

import java.util.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class ResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/hello-resteasy")
          .then()
             .statusCode(200)
             .body(is("Hello RESTEasy"));
    }

    @Test
    public void testInvoice() {
        Set<InvoiceServiceAPI.Beer> beers = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));
        beers.add(new InvoiceServiceAPI.Beer("weißbier", 2.00, 19));
        beers.add(new InvoiceServiceAPI.Beer("helles", 2.60, 8));
        beers.add(new InvoiceServiceAPI.Beer("starkbier", 2.30, 8));

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(beers)
                    .when().post("/invoice")
                    .then()
                        .statusCode(200)
                        .body(is("{\"totalSum\":7.672000000000001}"));
    }

}