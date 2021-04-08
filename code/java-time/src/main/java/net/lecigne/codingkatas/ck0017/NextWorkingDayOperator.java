package net.lecigne.codingkatas.ck0017;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.function.UnaryOperator;

public class NextWorkingDayOperator implements UnaryOperator<LocalDate> {

    @Override
    public LocalDate apply(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        int daysToAdd;
        if (dayOfWeek == DayOfWeek.FRIDAY) {
            daysToAdd = 3;
        } else if (dayOfWeek == DayOfWeek.SATURDAY) {
            daysToAdd = 2;
        } else {
            daysToAdd = 1;
        }
        return date.plusDays(daysToAdd);
    }

}
