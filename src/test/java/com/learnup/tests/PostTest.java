package com.learnup.tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.*;
import com.learnup.dto.Product;

import java.util.Properties;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PostTest {
    public static final String PRODUCT_ENDPOINT = "products";
    static Properties properties = new Properties();
    Faker faker = new Faker();

    static Product product;
    Integer id;

    @BeforeEach
    void setUp() {
        product = Product.builder()
                .price(100)
                .title(faker.food().dish())
                .categoryTitle("Food")
                .id(null)
                .build();
    }

    @Test
    void postProductPositiveTest() {
        Product response = given()
                .body(product)
                .header("Content-Type", "application/json")
                .log()
                .all()
                .expect()
                .statusCode(201)
                .when()
                .post("http://80.78.248.82:8189/market/api/v1/products")
                .prettyPeek()
                .body()
                .as(Product.class);
        id = response.getId();
        assertThat(response.getId(), is(not(nullValue())));
        assertThat(response.getTitle(), equalTo(product.getTitle()));
        assertThat(response.getPrice(), equalTo(product.getPrice()));
        assertThat(response.getCategoryTitle(), equalTo(product.getCategoryTitle()));
    }

    @Test
    void postProductWithNoTitleTest() {
        Product product2 = Product.builder()
                .price(100)
                .title("")
                .categoryTitle("Food")
                .id(null)
                .build();
        Product response = given()
                .body(product2)
                .header("Content-Type", "application/json")
                .log()
                .all()
                .expect()
                .statusCode(400)
                .when()
                .post("http://80.78.248.82:8189/market/api/v1/products")
                .prettyPeek()
                .body()
                .as(Product.class);
        id = response.getId();
        assertThat(response.getId(), is(not(nullValue())));
        assertThat(response.getTitle(), equalTo(product2.getTitle()));
        assertThat(response.getPrice(), equalTo(product2.getPrice()));
        assertThat(response.getCategoryTitle(), equalTo(product2.getCategoryTitle()));
    }

    @Test
    void postProductWithNullTitleTest() {
        Product product2 = Product.builder()
                .price(100)
                .title(null)
                .categoryTitle("Food")
                .id(null)
                .build();
        Product response = given()
                .body(product2)
                .header("Content-Type", "application/json")
                .log()
                .all()
                .expect()
                .statusCode(400)
                .when()
                .post("http://80.78.248.82:8189/market/api/v1/products")
                .prettyPeek()
                .body()
                .as(Product.class);
        id = response.getId();
        assertThat(response.getId(), is(not(nullValue())));
        assertThat(response.getTitle(), equalTo(product2.getTitle()));
        assertThat(response.getPrice(), equalTo(product2.getPrice()));
        assertThat(response.getCategoryTitle(), equalTo(product2.getCategoryTitle()));
    }

    @Test
    void postProductWithSpecialSymbolsInTitleTest() {
        Product product2 = Product.builder()
                .price(100)
                .title("$$$@@@^^^")
                .categoryTitle("Food")
                .id(null)
                .build();
        Product response = given()
                .body(product2)
                .header("Content-Type", "application/json")
                .log()
                .all()
                .expect()
                .statusCode(400)
                .when()
                .post("http://80.78.248.82:8189/market/api/v1/products")
                .prettyPeek()
                .body()
                .as(Product.class);
        id = response.getId();
        assertThat(response.getId(), is(not(nullValue())));
        assertThat(response.getTitle(), equalTo(product2.getTitle()));
        assertThat(response.getPrice(), equalTo(product2.getPrice()));
        assertThat(response.getCategoryTitle(), equalTo(product2.getCategoryTitle()));
    }

    @Test
    void postProductWithLongTitleTest() {
        Product product2 = Product.builder()
                .price(100)
                .title("LONGLONGLONGLONGLONGLONGLONGLONGLONGLONG")
                .categoryTitle("Food")
                .id(null)
                .build();
        Product response = given()
                .body(product2)
                .header("Content-Type", "application/json")
                .log()
                .all()
                .expect()
                .statusCode(400)
                .when()
                .post("http://80.78.248.82:8189/market/api/v1/products")
                .prettyPeek()
                .body()
                .as(Product.class);
        id = response.getId();
        assertThat(response.getId(), is(not(nullValue())));
        assertThat(response.getTitle(), equalTo(product2.getTitle()));
        assertThat(response.getPrice(), equalTo(product2.getPrice()));
        assertThat(response.getCategoryTitle(), equalTo(product2.getCategoryTitle()));
    }

    @Test
    void postProductWithNegativePriceTest() {
        Product product2 = Product.builder()
                .price(-100)
                .title(faker.food().fruit())
                .categoryTitle("Food")
                .id(null)
                .build();
        Product response = given()
                .body(product2)
                .header("Content-Type", "application/json")
                .log()
                .all()
                .expect()
                .statusCode(400)
                .when()
                .post("http://80.78.248.82:8189/market/api/v1/products")
                .prettyPeek()
                .body()
                .as(Product.class);
        id = response.getId();
        assertThat(response.getId(), is(not(nullValue())));
        assertThat(response.getTitle(), equalTo(product2.getTitle()));
        assertThat(response.getPrice(), equalTo(product2.getPrice()));
        assertThat(response.getCategoryTitle(), equalTo(product2.getCategoryTitle()));
    }

    @Test
    void postProductWithNullPriceTest() {
        Product product2 = Product.builder()
                .price(null)
                .title("Pig wing")
                .categoryTitle("Food")
                .id(null)
                .build();
        Product response = given()
                .body(product2)
                .header("Content-Type", "application/json")
                .log()
                .all()
                .expect()
                .statusCode(400)
                .when()
                .post("http://80.78.248.82:8189/market/api/v1/products")
                .prettyPeek()
                .body()
                .as(Product.class);
        id = response.getId();
        assertThat(response.getId(), is(not(nullValue())));
        assertThat(response.getTitle(), equalTo(product2.getTitle()));
        assertThat(response.getPrice(), equalTo(product2.getPrice()));
        assertThat(response.getCategoryTitle(), equalTo(product2.getCategoryTitle()));
    }

    @Test
    void postProductWithLongPriceTest() {
        Product product2 = Product.builder()
                .price(999999999)
                .title("Pig Wing")
                .categoryTitle("Food")
                .id(null)
                .build();
        Product response = given()
                .body(product2)
                .header("Content-Type", "application/json")
                .log()
                .all()
                .expect()
                .statusCode(400)
                .when()
                .post("http://80.78.248.82:8189/market/api/v1/products")
                .prettyPeek()
                .body()
                .as(Product.class);
        id = response.getId();
        assertThat(response.getId(), is(not(nullValue())));
        assertThat(response.getTitle(), equalTo(product2.getTitle()));
        assertThat(response.getPrice(), equalTo(product2.getPrice()));
        assertThat(response.getCategoryTitle(), equalTo(product2.getCategoryTitle()));
    }

  //  @AfterEach
    void tearDown() {
        when()
                .delete("http://80.78.248.82:8189/market/api/v1/products/{id}", id)
                .prettyPeek()
                .then()
                .statusCode(200);
    }
}