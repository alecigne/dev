package net.lecigne.currency.extensions;

import java.time.LocalDate;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Extensions {

  public static boolean isEqualOrAfter(LocalDate date1, LocalDate date2) {
    return !date1.isBefore(date2);
  }

}
