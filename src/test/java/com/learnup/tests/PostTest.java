package com.learnup.tests;

import com.learnup.dto.Product;
import io.restassured.RestAssured;
import org.junit.jupiter.api.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class PostTest {
    public static final String PRODUCT_ENDPOINT = "products";
    static Properties properties = new Properties();

    static Product product;
    Integer id;

    @BeforeEach
    void setUp() {
        product = Product.builder()
                .price(100)
                .title("Banana")
                .categoryTitle("Food")
                .build();
    }

    @Test
    void postProductPositiveTest() {
        id = given()
                .body(product.toString())
                .header("Content-Type", "application/json")
                .log()
                .all()
                .expect()
                .statusCode(201)
                .when()
                .post("http://80.78.248.82:8189/market/api/v1/products")
                .prettyPeek()
                .jsonPath()
                .get("id");
    }
    @Test
    void postProductWithNoTitleTest() {
        product.setTitle("");
        id = given()
                .body(product.toString())
                .header("Content-Type", "application/json")
                .log()
                .all()
                .expect()
                .statusCode(400)
                .when()
                .post("http://80.78.248.82:8189/market/api/v1/products")
                .prettyPeek()
                .jsonPath()
                .get("id");
    }
    @Test
    void postProductWithNullTitleTest() {
        product.setTitle(null);
        id = given()
                .body(product.toString())
                .header("Content-Type", "application/json")
                .log()
                .all()
                .expect()
                .statusCode(400)
                .when()
                .post("http://80.78.248.82:8189/market/api/v1/products")
                .prettyPeek()
                .jsonPath()
                .get("id");
    }
    @Test
    void postProductWithSpecialSymbolsInTitleTest() {
        product.setTitle("#$?");
        id = given()
                .body(product.toString())
                .header("Content-Type", "application/json")
                .log()
                .all()
                .expect()
                .statusCode(400)
                .when()
                .post("http://80.78.248.82:8189/market/api/v1/products")
                .prettyPeek()
                .jsonPath()
                .get("id");
    }
    @Test
    void postProductWithLongTitleTest() {
        product.setTitle("LONGLONGLONGLONGLONGLONGLONGLONGLONGLONG");
        id = given()
                .body(product.toString())
                .header("Content-Type", "application/json")
                .log()
                .all()
                .expect()
                .statusCode(400)
                .when()
                .post("http://80.78.248.82:8189/market/api/v1/products")
                .prettyPeek()
                .jsonPath()
                .get("id");
    }
    @Test
    void postProductWithNegativePriceTest() {
        product.setPrice(-1);
        id = given()
                .body(product.toString())
                .header("Content-Type", "application/json")
                .log()
                .all()
                .expect()
                .statusCode(400)
                .when()
                .post("http://80.78.248.82:8189/market/api/v1/products")
                .prettyPeek()
                .jsonPath()
                .get("id");
    }
    @Test
    void postProductWithNullPriceTest() {
        product.setPrice(null);
        id = given()
                .body(product.toString())
                .header("Content-Type", "application/json")
                .log()
                .all()
                .expect()
                .statusCode(400)
                .when()
                .post("http://80.78.248.82:8189/market/api/v1/products")
                .prettyPeek()
                .jsonPath()
                .get("id");
    }
    @Test
    void postProductWithLongPriceTest() {
        product.setPrice(999999999);
        id = given()
                .body(product.toString())
                .header("Content-Type", "application/json")
                .log()
                .all()
                .expect()
                .statusCode(400)
                .when()
                .post("http://80.78.248.82:8189/market/api/v1/products")
                .prettyPeek()
                .jsonPath()
                .get("id");
    }

//    @AfterEach
    void tearDown() {
        when()
                .delete("http://80.78.248.82:8189/market/api/v1/products/{id}", id)
                .prettyPeek()
                .then()
                .statusCode(200);
    }
}