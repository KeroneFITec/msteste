package com.iubi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Locale;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.MessageSource;

import io.qase.api.exceptions.QaseException;
import io.qase.client.ApiClient;
import io.qase.client.api.ResultsApi;
import io.qase.client.model.ResultCreate;
import io.qase.client.model.ResultCreate.StatusEnum;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HelloControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;
	@Autowired
    private MessageSource messageSource;

    @Test
    public void testHelloEndpoint() {
    	Locale locale = new Locale("pt", "BR");
    	
        String response = restTemplate.getForObject("/hello", String.class);
        assertEquals(messageSource.getMessage("hello.from.springboot", null, locale), response);

//        String token = "your_api_token";
//        String projectCode = "PRJ";
//
//        ApiClient client = new ApiClient();
//        client.setAccessToken(token);
//
//        ResultsApi resultsApi = new ResultsApi(client);
//
//        ResultCreate result = new ResultCreate()
//            .caseId(1L)
//            .status(StatusEnum.PASSED)
//            .comment("Hello endpoint test passed.");
//
//        try {
//			resultsApi.createResult(projectCode, 1, result);
//		} catch (QaseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

    }
}
