package net.lecigne.dev.service;

import io.micronaut.runtime.event.annotation.EventListener;
import io.micronaut.runtime.server.event.ServerStartupEvent;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.util.List;

@Singleton
public class GlobalService {

  private final List<DummyService> services;

  @Inject
  public GlobalService(List<DummyService> services) {
    this.services = services;
  }

  @EventListener
  public void executeServices(ServerStartupEvent unused) {
    services.stream()
        .filter(s -> s.getClass().getName().equals("net.lecigne.dev.service.ReactiveService"))
        .findFirst()
        .orElseThrow()
        .doIt();
  }

}
