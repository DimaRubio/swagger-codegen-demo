package com.petstore.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.http.ContentType;

import java.util.function.Supplier;

import static com.petstore.client.invoker.GsonObjectMapper.gson;
import static io.restassured.config.ObjectMapperConfig.objectMapperConfig;
import static io.restassured.config.RestAssuredConfig.config;

public class ApiClientUtils {

    public static final String BASE_URL = "http://localhost:8080/v2";

    public static Supplier<RequestSpecBuilder> supplier() {
        return () -> new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setConfig(config().objectMapperConfig(objectMapperConfig().defaultObjectMapper(gson())))
                .setBaseUri(BASE_URL)
                .addFilter(new ErrorLoggingFilter());
        //.addFilter(new RequestLoggingFilter())
        //.addFilter(new ResponseLoggingFilter());
    }
}
