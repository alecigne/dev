package net.lecigne.dev.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.multipart.CompletedFileUpload;
import jakarta.inject.Inject;
import net.lecigne.dev.service.ReadExcelService;

@Controller
public class ReadExcelController {

  private final ReadExcelService service;

  @Inject
  public ReadExcelController(ReadExcelService service) {
    this.service = service;
  }

  @Post(consumes = MediaType.MULTIPART_FORM_DATA)
  public HttpResponse<String> createMembersFromExcelFile(CompletedFileUpload file) {
    service.processFile(file);
    return HttpResponse.ok();
  }

}
