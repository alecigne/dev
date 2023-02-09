package net.lecigne.dev.service;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.util.List;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import net.lecigne.dev.client.BatchingAsyncParallelDummyClient;

@Singleton
@Slf4j
public class AsyncParallelService implements DummyService {

  private final BatchingAsyncParallelDummyClient client;

  @Inject
  public AsyncParallelService(BatchingAsyncParallelDummyClient client) {
    this.client = client;
  }

  @Override
  public void doIt() {
    log.info("=== BatchingAsyncParallel ===");
    client
        .getDummyResponseOne(getListOfIds())
        .thenAccept(l -> l.forEach(System.out::println));
    client
        .getDummyResponseTwo(getListOfIds())
        .thenAccept(l -> l.forEach(System.out::println));
    client
        .getDummyResponseThree(getListOfIds())
        .thenAccept(l -> l.forEach(System.out::println));
  }

  private static List<String> getListOfIds() {
    return IntStream.rangeClosed(1, 10).mapToObj(i -> "id" + i).toList();
  }

}
