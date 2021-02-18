package net.lecigne.catfactsspringboot;

import feign.Client;
import feign.Feign;
import feign.Request;
import feign.Request.Options;
import feign.Response;
import feign.Retryer;
import net.lecigne.catfactsspringboot.client.AnimalFactsClient;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Collections;

import static feign.Request.HttpMethod.GET;
import static feign.Util.UTF_8;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@SpringBootTest
class CatFactsSpringBootApplicationTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(CatFactsSpringBootApplicationTests.class);

    @Mock
    Client mockClient;

    @Test
    void testSuccess() throws IOException {
        // Given
        given(mockClient.execute(any(Request.class), any(Options.class)))
                .willReturn(Response.builder()
                        .status(200)
                        .request(Request.create(GET, "/api", Collections.emptyMap(), null, UTF_8))
                        .build());

        AnimalFactsClient factsClient = Feign.builder()
                .client(mockClient)
                .target(AnimalFactsClient.class, "https://cat-fact.herokuapp.com");

        // When
        factsClient.getFacts("dog", 1);

        // Then
        then(mockClient)
                .should()
                .execute(any(Request.class), any(Options.class));
    }

    @Test
    void testDefaultRetryerGivingUp() throws IOException {
        // Given
        given(mockClient.execute(any(Request.class), any(Options.class)))
                .willThrow(new UnknownHostException());

        AnimalFactsClient factsClient = Feign.builder()
                .client(mockClient)
                .target(AnimalFactsClient.class, "https://cat-fact.herokuapp.com");

        // When
        try {
            factsClient.getFacts("dog", 1);
            fail("The client should fail!");
        } catch (final Exception e) {
            LOGGER.warn(e::getMessage);
        } finally {
            // Then
            then(mockClient)
                    .should(times(5))
                    .execute(any(Request.class), any(Options.class));
        }
    }

    @Test
    void testCustomRetryer() throws IOException {
        // Given
        given(mockClient.execute(any(Request.class), any(Options.class)))
                .willThrow(new UnknownHostException());

        int maxAttempts = 3;
        AnimalFactsClient factsClient = Feign.builder()
                .client(mockClient)
                .retryer(new Retryer.Default(1, 100, maxAttempts))
                .target(AnimalFactsClient.class, "https://cat-fact.herokuapp.com");

        try {
            // When
            factsClient.getFacts("dog", 1);
            fail("The client should fail!");
        } catch (final Exception e) {
            LOGGER.warn(e::getMessage);
        } finally {
            // Then
            then(mockClient)
                    .should(times(maxAttempts))
                    .execute(any(Request.class), any(Options.class));
        }
    }
}
