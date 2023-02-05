package net.lecigne.dev.service;

import io.micronaut.http.multipart.CompletedFileUpload;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

@Singleton
@Slf4j
public class ReadExcelService {

  public void processFile(CompletedFileUpload file) {
    log.info("Filename: {}", file.getFilename());
  }

}
