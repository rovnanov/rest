package com.learnup.tests.product;

import com.learnup.dto.Product;
import com.learnup.tests.BaseTest;
import io.qameta.allure.*;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import static com.learnup.asserts.CommonAsserts.getProductTest;
import static com.learnup.Endpoints.POST_PRODUCT_ENDPOINT;
import static com.learnup.Endpoints.PRODUCT_ID_ENDPOINT;
import static com.learnup.enums.ProductType.*;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static com.learnup.dto.Product.builder;
@Epic("Product tests")
@Story("Product GET tests")
@Severity(SeverityLevel.NORMAL)
public class GetTest extends BaseTest {
    @Test
    @Description("GET product TEST")
    void positiveGetOrange() {
        Product response = given()
                .when()
                .get(PRODUCT_ID_ENDPOINT, ORANGE.getId())
                .prettyPeek()
                .body()
                .as(Product.class);
        getProductTest(response, ORANGE);
    }
}
