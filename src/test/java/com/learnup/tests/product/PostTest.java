package com.learnup.tests.product;

import com.github.javafaker.Faker;
import com.github.javafaker.Food;
import com.learnup.tests.BaseTest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.*;
import com.learnup.dto.Product;

import java.util.Properties;

import static com.learnup.asserts.CommonAsserts.postProductTest;

import static com.learnup.asserts.CommonAsserts.postProductPositiveAssert;
import static com.learnup.Endpoints.POST_PRODUCT_ENDPOINT;
import static com.learnup.Endpoints.PRODUCT_ID_ENDPOINT;
import static com.learnup.enums.CategoryType.FOOD;
import static com.learnup.tests.BaseTest.deleteResponseSpec;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PostTest extends BaseTest {
    Faker faker = new Faker();
    Product product;
    Product nulltitleproduct;
    Integer id;
    RequestSpecification postProductRequestSpec;
    ResponseSpecification postProductResponseSpec;

    @BeforeEach
    void setUp() {
        product = Product.builder()
                .price(faker.number().randomDigitNotZero())
                .title(faker.food().dish())
                .categoryTitle(FOOD.getName())
                .build();
        nulltitleproduct = Product.builder()
                .price(faker.number().randomDigitNotZero())
                .title("")
                .categoryTitle(FOOD.getName())
                .build();
        postProductRequestSpec = new RequestSpecBuilder()
                .setBody(product)
                .setContentType(ContentType.JSON)
                .build();
        postProductResponseSpec = new ResponseSpecBuilder()
                .expectStatusCode(201)
                .expectStatusLine("HTTP/1.1 201 ")
                .expectBody("title", equalTo(product.getTitle()))
                .expectBody("price", equalTo(product.getPrice()))
                .expectBody("categoryTitle", equalTo(product.getCategoryTitle()))
                .build();
    }

    @Test
    void postProductPositiveTest() {
        Product response = given(postProductRequestSpec, postProductResponseSpec)
                .post(POST_PRODUCT_ENDPOINT)
                .prettyPeek()
                .body()
                .as(Product.class);
        id = postProductTest(response, product);
    }

    @Test
    void postProductWithNullTitleTest() {
        postProductResponseSpec = new ResponseSpecBuilder()
                .expectStatusCode(400)
                .build();
        postProductRequestSpec = new RequestSpecBuilder()
                .setBody(nulltitleproduct)
                .setContentType(ContentType.JSON)
                .build();

        Product response = given(postProductRequestSpec, postProductResponseSpec)
                .post(POST_PRODUCT_ENDPOINT)
                .prettyPeek()
                .body()
                .as(Product.class);
        id = postProductTest(response, nulltitleproduct);
    }

    @Test
    void postProductWithEmptyTitleTest() {
        product = Product.builder()
                .price(5)
                .title("")
                .categoryTitle(FOOD.getName())
                .build();
        postProductResponseSpec = new ResponseSpecBuilder()
                .expectStatusCode(201)
                .build();
        postProductRequestSpec = new RequestSpecBuilder()
                .setBody(product)
                .setContentType(ContentType.JSON)
                .build();

        Product response = given(postProductRequestSpec, postProductResponseSpec)
                .post(POST_PRODUCT_ENDPOINT)
                .prettyPeek()
                .body()
                .as(Product.class);
        id = postProductTest(response, product);
    }

    @Test
    void postProductWithSpecialSymbolsInTitleTest() {
        product = Product.builder()
                .price(faker.number().randomDigitNotZero())
                .title("@#$%^")
                .categoryTitle(FOOD.getName())
                .build();
        postProductResponseSpec = new ResponseSpecBuilder()
                .expectStatusCode(400)
                .build();
        postProductRequestSpec = new RequestSpecBuilder()
                .setBody(product)
                .setContentType(ContentType.JSON)
                .build();

        Product response = given(postProductRequestSpec, postProductResponseSpec)
                .post(POST_PRODUCT_ENDPOINT)
                .prettyPeek()
                .body()
                .as(Product.class);

        id = postProductTest(response, product);
    }

    @Test
    void postProductWithLongTitleTest() {
        product = Product.builder()
                .price(faker.number().randomDigitNotZero())
                .title("LONGLONGLONGLONGLONGLONGLONG")
                .categoryTitle(FOOD.getName())
                .build();
        postProductResponseSpec = new ResponseSpecBuilder()
                .expectStatusCode(400)
                .build();
        postProductRequestSpec = new RequestSpecBuilder()
                .setBody(product)
                .setContentType(ContentType.JSON)
                .build();

        Product response = given(postProductRequestSpec, postProductResponseSpec)
                .post(POST_PRODUCT_ENDPOINT)
                .prettyPeek()
                .body()
                .as(Product.class);

        id = postProductTest(response, product);
    }

    @Test
    void postProductWithNegativePriceTest() {
        product = Product.builder()
                .price(-100)
                .title(faker.food().sushi())
                .categoryTitle(FOOD.getName())
                .build();
        postProductResponseSpec = new ResponseSpecBuilder()
                .expectStatusCode(400)
                .build();
        postProductRequestSpec = new RequestSpecBuilder()
                .setBody(product)
                .setContentType(ContentType.JSON)
                .build();

        Product response = given(postProductRequestSpec, postProductResponseSpec)
                .post(POST_PRODUCT_ENDPOINT)
                .prettyPeek()
                .body()
                .as(Product.class);

        id = postProductTest(response, product);
    }

    @Test
    void postProductWithNullPriceTest() {
        product = Product.builder()
                .price(null)
                .title(faker.food().vegetable())
                .categoryTitle(FOOD.getName())
                .build();
        postProductResponseSpec = new ResponseSpecBuilder()
                .expectStatusCode(400)
                .build();
        postProductRequestSpec = new RequestSpecBuilder()
                .setBody(product)
                .setContentType(ContentType.JSON)
                .build();

        Product response = given(postProductRequestSpec, postProductResponseSpec)
                .post(POST_PRODUCT_ENDPOINT)
                .prettyPeek()
                .body()
                .as(Product.class);
        id = postProductTest(response, product);
    }

    @Test
    void postProductWithLongPriceTest() {
        product = Product.builder()
                .price(999999999)
                .title(faker.food().sushi())
                .categoryTitle(FOOD.getName())
                .build();
        postProductResponseSpec = new ResponseSpecBuilder()
                .expectStatusCode(400)
                .build();
        postProductRequestSpec = new RequestSpecBuilder()
                .setBody(product)
                .setContentType(ContentType.JSON)
                .build();

        Product response = given(postProductRequestSpec, postProductResponseSpec)
                .post(POST_PRODUCT_ENDPOINT)
                .prettyPeek()
                .body()
                .as(Product.class);

        id = postProductTest(response, product);
    }

    @AfterEach
    void tearDown() {
        given()
                .response()
                .spec(deleteResponseSpec)
                .when()
                .delete(PRODUCT_ID_ENDPOINT, id)
                .prettyPeek()
                .then()
                .statusCode(200);
    }
}