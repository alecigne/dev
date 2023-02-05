package net.lecigne.dev.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

@Controller("/age")
@Slf4j
public class DummyController {

  @Get
  public Map<String, Integer> getAge(List<String> ids) throws InterruptedException {
    log.info("Waiting 2 seconds...");
    Thread.sleep(2000);
    return ids.stream().collect(Collectors.toMap(
        Function.identity(),
        unused -> 18
    ));
  }

}
