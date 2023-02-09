package net.lecigne.dev.service;

import io.micronaut.runtime.event.annotation.EventListener;
import io.micronaut.runtime.server.event.ServerStartupEvent;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.util.List;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import net.lecigne.dev.client.BatchingSyncDummyClient;

@Singleton
@Slf4j
public class SyncService implements DummyService {

  private final BatchingSyncDummyClient client;

  @Inject
  public SyncService(BatchingSyncDummyClient client) {
    this.client = client;
  }

  @Override
  public void doIt() {
    log.info("=== BatchingSync ===");
    List<Double> dummyResponseOne = client.getDummyResponseOne(getListOfIds());
    dummyResponseOne.forEach(System.out::println);
    List<Double> dummyResponseTwo = client.getDummyResponseTwo(getListOfIds());
    dummyResponseTwo.forEach(System.out::println);
    List<Double> dummyResponseThree = client.getDummyResponseThree(getListOfIds());
    dummyResponseThree.forEach(System.out::println);
  }

  private static List<String> getListOfIds() {
    return IntStream.rangeClosed(1, 4).mapToObj(i -> "id" + i).toList();
  }

}
