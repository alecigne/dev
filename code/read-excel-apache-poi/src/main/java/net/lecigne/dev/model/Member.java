package net.lecigne.dev.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.lecigne.dev.excel.ExcelColumn;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class Member {
  @ExcelColumn(name = "ID")
  private String id;

  @ExcelColumn(name = "First name")
  private String firstName;

  @ExcelColumn(name = "Last name")
  private String lastName;

  @ExcelColumn(name = "Email")
  private String email;

  @Setter
  private int age;
}
