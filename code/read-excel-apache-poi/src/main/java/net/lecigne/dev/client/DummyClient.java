package net.lecigne.dev.client;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.http.client.annotation.Client;
import java.util.List;
import java.util.Map;

@Client("/age")
public interface DummyClient {

  @Get
  Map<String, Integer> getAgeById(@QueryValue List<String> ids);

}
