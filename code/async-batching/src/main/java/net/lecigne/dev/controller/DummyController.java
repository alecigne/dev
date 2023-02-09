package net.lecigne.dev.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Controller("/dummyApi")
@Slf4j
public class DummyController {

  @Get("/one")
  public List<Double> getDummyResponseOne(@QueryValue List<String> ids) {
    log.info("getDummyResponseOne is receiving a request...");
    return sleepAndGet(ids);
  }

  @Get("/two")
  public List<Double> getDummyResponseTwo(@QueryValue List<String> ids) {
    log.info("getDummyResponseTwo is receiving a request...");
    return sleepAndGet(ids);
  }

  @Get("/three")
  public List<Double> getDummyResponseThree(@QueryValue List<String> ids) {
    log.info("getDummyResponseThree is receiving a request...");
    return sleepAndGet(ids);
  }

  private static List<Double> sleepAndGet(List<String> ids) {
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    return ids.stream().map(unused -> Math.random()).toList();
  }

}
