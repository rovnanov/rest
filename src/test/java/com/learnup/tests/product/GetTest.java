package com.learnup.tests.product;

import com.learnup.dto.Product;
import com.learnup.tests.BaseTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import static com.learnup.Endpoints.POST_PRODUCT_ENDPOINT;
import static com.learnup.Endpoints.PRODUCT_ID_ENDPOINT;
import static com.learnup.enums.ProductType.ORANGE;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static com.learnup.dto.Product.builder;

public class GetTest extends BaseTest {
    @Test
    void positiveGetProduct(){
        Product response = given()
                .when()
                .get(PRODUCT_ID_ENDPOINT, ORANGE.getId())
                .prettyPeek()
                .body()
                .as(Product.class);
        assertThat(response.getId(), equalTo(ORANGE.getId()));
        assertThat(response.getTitle(), equalTo(ORANGE.getTitle()));
        assertThat(response.getPrice(), equalTo(ORANGE.getPrice()));
        assertThat(response.getCategoryTitle(), equalTo(ORANGE.getCategory()));
    }
}
