package net.lecigne.dev.service;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.util.List;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import net.lecigne.dev.client.ReactiveDummyClient;

@Singleton
@Slf4j
public class ReactiveService implements DummyService {

  private final ReactiveDummyClient client;

  @Inject
  public ReactiveService(ReactiveDummyClient client) {
    this.client = client;
  }

  @Override
  public void doIt() {
    log.info("=== ReactiveDummyClient ===");
    client.getDummyResponseOne(getListOfIds()).subscribe(l -> l.forEach(System.out::println));
    client.getDummyResponseTwo(getListOfIds()).subscribe(l -> l.forEach(System.out::println));
    client.getDummyResponseThree(getListOfIds()).subscribe(l -> l.forEach(System.out::println));
  }

  private static List<String> getListOfIds() {
    return IntStream.rangeClosed(1, 4).mapToObj(i -> "id" + i).toList();
  }

}
