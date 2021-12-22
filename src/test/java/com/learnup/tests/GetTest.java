package com.learnup.tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class GetTest {
    public static final String PRODUCT_ENDPOINT = "products/{id}";
    static Properties properties = new Properties();

    @BeforeAll
    static void setup() throws IOException {
        properties.load(new FileInputStream("src/test/resources/application.properties"));
        RestAssured.baseURI = properties.getProperty("baseURL");
    }

    @Test
    void getAllProducts() {
        given()
                .when()
                .log()
                .method()
                .log()
                .uri()
                .log()
                .headers()
                .log()
                .body()
                .when()
                .get("http://80.78.248.82:8189/market/api/v1/products")
                .prettyPeek()
                .then()
                .statusCode(200);
    }
}
