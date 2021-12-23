package com.learnup.tests.product;

import com.learnup.dto.Product;
import com.learnup.tests.BaseTest;
import io.qameta.allure.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.learnup.asserts.CommonAsserts.putProductTest;
import static com.learnup.Endpoints.POST_PRODUCT_ENDPOINT;
import static com.learnup.Endpoints.PRODUCT_ID_ENDPOINT;
import static com.learnup.enums.CategoryType.FOOD;
import static com.learnup.enums.ProductType.BLACKBERRIES;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static java.util.function.Predicate.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

@Epic("Product tests")
@Story("Product PUT tests")
@Severity(SeverityLevel.NORMAL)

public class PutTest extends BaseTest {
    Product product;
    Integer id;

    RequestSpecification postProductRequestSpec;
    ResponseSpecification postProductResponseSpec;

    @BeforeEach
    void setUp() {
        product = Product.builder()
                .id(BLACKBERRIES.getId())
                .price(150)
                .title(BLACKBERRIES.getTitle())
                .categoryTitle(BLACKBERRIES.getCategory())
                .build();
        postProductRequestSpec = new RequestSpecBuilder()
                .setBody(product)
                .setContentType(ContentType.JSON)
                .build();
        postProductResponseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectStatusLine("HTTP/1.1 200 ")
                .expectBody("title", equalTo(product.getTitle()))
                .expectBody("price", equalTo(product.getPrice()))
                .expectBody("categoryTitle", equalTo(product.getCategoryTitle()))
                .build();
    }

    @Test
    @Description("PUT positive TEST")
    void putNewParametersTest() {
        Product response = given(postProductRequestSpec, postProductResponseSpec)
                .put(POST_PRODUCT_ENDPOINT)
                .prettyPeek()
                .body()
                .as(Product.class);
        id = putProductTest(response, product);
    }

    @Test
    @Description("PUT NULL title TEST")
    void putNullTitlesTest() {
        product = Product.builder()
                .id(BLACKBERRIES.getId())
                .price(10)
                .title(null)
                .categoryTitle(BLACKBERRIES.getCategory())
                .build();
        postProductResponseSpec = new ResponseSpecBuilder()
                .expectStatusCode(400)
                .build();
        postProductRequestSpec = new RequestSpecBuilder()
                .setBody(product)
                .setContentType(ContentType.JSON)
                .build();

        Product response = given(postProductRequestSpec, postProductResponseSpec)
                .put(POST_PRODUCT_ENDPOINT)
                .prettyPeek()
                .body()
                .as(Product.class);

        id = putProductTest(response, product);
    }

    @AfterEach
    @Step("CHECK product VALUES")
    void check() {
        given()
                .when()
                .get(PRODUCT_ID_ENDPOINT, id)
                .prettyPeek();
    }
}
