package net.lecigne.dev.excel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class MemberDTO {
  @ExcelColumn(name = "ID")
  private String id;

  @ExcelColumn(name = "First name")
  private String firstName;

  @ExcelColumn(name = "Last name")
  private String lastName;

  @ExcelColumn(name = "Email")
  private String email;
}
