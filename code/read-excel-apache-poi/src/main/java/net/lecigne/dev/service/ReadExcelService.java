package net.lecigne.dev.service;

import static java.util.stream.Collectors.summingInt;

import com.google.common.collect.Lists;
import io.micronaut.http.multipart.CompletedFileUpload;
import io.micronaut.scheduling.annotation.Async;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import net.lecigne.dev.client.DummyClient;
import net.lecigne.dev.excel.MemberDTO;
import net.lecigne.dev.model.Member;
import net.lecigne.dev.excel.ExcelFileUtils;

@Singleton
@Slf4j
public class ReadExcelService {

  private final DummyClient client;

  @Inject
  public ReadExcelService(DummyClient client) {
    this.client = client;
  }

  public void processFile(CompletedFileUpload file) {
    try {
      List<MemberDTO> members = ExcelFileUtils.sheetToPOJO(file.getInputStream(), MemberDTO.class);
      asyncProcessing(members);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Async
  public void asyncProcessing(List<MemberDTO> memberDTOs) {
    log.info("Async process is starting...");
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    log.info("Starting calls to webservice...");
    List<List<MemberDTO>> membersSplit = Lists.partition(memberDTOs, 3);
    Map<String, Integer> ageById = membersSplit
        .parallelStream()
        .map(listOfMembers -> listOfMembers.stream().map(MemberDTO::getId).toList())
        .map(client::getAgeById)
        .flatMap(m -> m.entrySet().stream())
        .collect(Collectors.groupingBy(Entry::getKey, summingInt(Entry::getValue)));
    List<Member> members = memberDTOs.stream().map(dto -> Member.builder()
            .id(dto.getId())
            .firstName(dto.getFirstName())
            .lastName(dto.getLastName())
            .email(dto.getEmail())
            .age(ageById.get(dto.getId()))
            .build())
        .toList();
    log.info("Success.");
    members.forEach(System.out::println);
  }

}
