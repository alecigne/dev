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
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

@Singleton
public class BatchingAsyncParallelDummyClient {

  private final HttpClient httpClient;

  @Inject
  public BatchingAsyncParallelDummyClient(@Client("/dummyApi") HttpClient httpClient) {
    this.httpClient = httpClient;
  }

  public CompletableFuture<List<Double>> getDummyResponseOne(List<String> ids) {
    return call(ids, "/one");
  }

  public CompletableFuture<List<Double>> getDummyResponseTwo(List<String> ids) {
    return call(ids, "/two");
  }

  public CompletableFuture<List<Double>> getDummyResponseThree(List<String> ids) {
    return call(ids, "/three");
  }

  private CompletableFuture<List<Double>> call(List<String> ids, String path) {
    return CompletableFuture.supplyAsync(() -> {
      ForkJoinPool forkJoinPool = new ForkJoinPool(5);
      try {
        return forkJoinPool.submit(() ->
            Lists.partition(ids, 2)
                .parallelStream()
                .flatMap(batch -> {
                  String uri = UriBuilder.of(path).queryParam("ids", batch.toArray()).toString();
                  return httpClient.toBlocking().retrieve(HttpRequest.GET(uri), Argument.listOf(Double.class)).stream();
                })
                .toList()
        ).get();
      } catch (InterruptedException | ExecutionException e) {
        throw new RuntimeException(e);
      }
    });
  }

}
