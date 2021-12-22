package com.learnup.tests;

import com.learnup.dto.Product;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static com.learnup.dto.Product.builder;

public class GetTest {
    public static final String PRODUCT_ENDPOINT = "products/{id}";
    static Properties properties = new Properties();


    @BeforeAll
    static void setup() throws IOException {
        properties.load(new FileInputStream("src/test/resources/application.properties"));
        RestAssured.baseURI = properties.getProperty("baseURL");
    }

    @Test
    void getProductPositiveTest() {
        Product response = given()
                .when()
                .log()
                .method()
                .log()
                .uri()
                .log()
                .headers()
                .log()
                .body()
                .expect()
                .statusCode(200)
                .when()
                .get(PRODUCT_ENDPOINT, 20542)
                .prettyPeek()
                .body()
                .as(Product.class);
        assertThat(response.getId(), equalTo(response.getId()));
        assertThat(response.getTitle(), equalTo(response.getTitle()));
        assertThat(response.getPrice(), equalTo(response.getPrice()));
        assertThat(response.getCategoryTitle(), equalTo(response.getCategoryTitle()));
    }

    @Test
    void getProductWithNegativeID() {
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
                .get(PRODUCT_ENDPOINT, "-500")
                .prettyPeek()
                .then()
                .statusCode(404);
    }

    @Test
    void getProductWithNullStringID() {
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
                .get(PRODUCT_ENDPOINT, "null")
                .prettyPeek()
                .then()
                .statusCode(400);
    }

    @Test
    void FindPostedProduct() {
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
                .get(PRODUCT_ENDPOINT, 20542)
                .prettyPeek()
                .then()
                .statusCode(200);
    }
}
