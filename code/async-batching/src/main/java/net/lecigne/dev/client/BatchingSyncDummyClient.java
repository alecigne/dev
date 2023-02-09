package net.lecigne.dev.client;

import com.google.common.collect.Lists;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.uri.UriBuilder;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.util.List;

@Singleton
public class BatchingSyncDummyClient {

  private final HttpClient httpClient;

  @Inject
  public BatchingSyncDummyClient(@Client("/dummyApi") HttpClient httpClient) {
    this.httpClient = httpClient;
  }

  public List<Double> getDummyResponseOne(List<String> ids) {
    return call(ids, "/one");
  }

  public List<Double> getDummyResponseTwo(List<String> ids) {
    return call(ids, "/two");
  }

  public List<Double> getDummyResponseThree(List<String> ids) {
    return call(ids, "/three");
  }

  private List<Double> call(List<String> ids, String path) {
    return Lists.partition(ids, 2)
        .stream()
        .flatMap(batch -> {
          String uri = UriBuilder.of(path).queryParam("ids", batch.toArray()).toString();
          return httpClient.toBlocking().retrieve(HttpRequest.GET(uri), Argument.listOf(Double.class)).stream();
        })
        .toList();
  }

}
