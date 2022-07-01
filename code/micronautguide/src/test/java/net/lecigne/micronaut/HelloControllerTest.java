package net.lecigne.micronaut;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@MicronautTest
class HelloControllerTest {

    @Inject
    @Client("/")
    HttpClient client;

    @Test
    void index() {
        // Given
        HttpRequest<String> request = HttpRequest.GET("/hello");
        String expected = "Hello world";

        // When
        String result = client.toBlocking().retrieve(request);

        // Then
        assertThat(result)
                .isNotNull()
                .isEqualTo(expected);
    }

}
