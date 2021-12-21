package com.learnup.tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class CategoryTest {
    public static final String CATEGORY_ENDPOINT ="categories/{id}";
    static Properties properties = new Properties();

    @BeforeEach
    void setUP() throws IOException {
        properties.load(new FileInputStream("application.properties"));
        RestAssured.baseURI = properties.getProperty("baseURL");
    }
}

