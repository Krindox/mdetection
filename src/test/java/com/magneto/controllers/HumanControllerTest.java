package com.magneto.controllers;
import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.internal.testutils.AwsProxyRequestBuilder;
import com.amazonaws.serverless.proxy.internal.testutils.MockLambdaContext;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.services.lambda.runtime.Context;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.magneto.Entities.Human;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpMethod;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import io.micronaut.function.aws.proxy.MicronautLambdaHandler;
import java.util.Arrays;


public class HumanControllerTest {
    private static MicronautLambdaHandler handler;
    private static Context lambdaContext = new MockLambdaContext();
    private static ObjectMapper objectMapper;

    @BeforeAll
    public static void setupSpec() {
        try {
            handler = new MicronautLambdaHandler();
            objectMapper = handler.getApplicationContext().getBean(ObjectMapper.class);

        } catch (ContainerInitializationException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    public static void cleanupSpec() {
        handler.getApplicationContext().close();
    }

    @Test
    void apiMutantTest() throws JsonProcessingException {
        Human human = new Human();
        human.setDna(Arrays.asList("ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"));
        String json = objectMapper.writeValueAsString(human);
        AwsProxyRequest request = new AwsProxyRequestBuilder("/mutant/", HttpMethod.POST.toString())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .body(json)
                .build();
        AwsProxyResponse response = handler.handleRequest(request, lambdaContext);
        Assertions.assertEquals(HttpStatus.OK.getCode(), response.getStatusCode());
    }

    @Test
    void apiNotMutantTest() throws JsonProcessingException {
        Human human = new Human();
        human.setDna(Arrays.asList("ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTG"));
        String json = objectMapper.writeValueAsString(human);
        AwsProxyRequest request = new AwsProxyRequestBuilder("/mutant/", HttpMethod.POST.toString())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .body(json)
                .build();
        AwsProxyResponse response = handler.handleRequest(request, lambdaContext);
        Assertions.assertEquals(HttpStatus.FORBIDDEN.getCode(), response.getStatusCode());
    }
}
