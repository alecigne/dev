package net.lecigne.dev.client;

import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.uri.UriBuilder;
import jakarta.inject.Inject;
import java.util.List;
import reactor.core.publisher.Mono;

public class ReactiveDummyClient {

  private final HttpClient httpClient;

  @Inject
  public ReactiveDummyClient(@Client("/dummyApi") HttpClient httpClient) {
    this.httpClient = httpClient;
  }

  public Mono<List<Double>> getDummyResponseOne(List<String> ids) {
    return call(ids, "/one");
  }

  public Mono<List<Double>> getDummyResponseTwo(List<String> ids) {
    return call(ids, "/two");
  }

  public Mono<List<Double>> getDummyResponseThree(List<String> ids) {
    return call(ids, "/three");
  }

  private Mono<List<Double>> call(List<String> ids, String path) {
    String uri = UriBuilder.of(path).queryParam("ids", ids.toArray()).toString();
    return Mono.from(httpClient.retrieve(HttpRequest.GET(uri), Argument.listOf(Double.class)));
  }

}
