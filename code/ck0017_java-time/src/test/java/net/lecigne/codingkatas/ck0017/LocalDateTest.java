package net.lecigne.codingkatas.ck0017;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.time.Clock;
import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;

import static java.time.DayOfWeek.SUNDAY;
import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.MONTHS;
import static java.time.temporal.ChronoUnit.YEARS;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * A LocalDate tutorial in the form of unit tests.
 *
 * @author Anthony Le Cigne
 */
class LocalDateTest {

    @Test
    void now_givenClock_shouldReturnRightDate() {
        String dateExpected = "2016-07-01";
        Clock clock = Clock.fixed(Instant.parse(dateExpected + "T12:00:00.00Z"), ZoneId.of("UTC"));
        LocalDate date = LocalDate.now(clock);
        assertThat(date).hasToString(dateExpected);
    }

    @Test
    void of_givenDate_shouldReturnRightDate() {
        assertThat(LocalDate.of(2011, 11, 12)).hasToString("2011-11-12");
    }

    @Test
    void of_givenWrongDate_shouldThrowException() {
        Executable wrongLeap = () -> LocalDate.of(2011, 2, 29);
        Executable wrongMonth = () -> LocalDate.of(2011, 13, 10);
        Executable wrongDay = () -> LocalDate.of(2011, 10, 42);
        asList(wrongLeap, wrongMonth, wrongDay).forEach(exec -> assertThrows(DateTimeException.class, exec));
    }

    @Test
    void parse_givenFormattedDate_shouldReturnRightDate() {
        String sourceDate = "2013-04-12";
        assertThat(LocalDate.parse(sourceDate)).hasToString(sourceDate);
    }

    @Test
    void parse_givenWrongDate_shouldThrowException() {
        Executable wrongLeap = () -> LocalDate.parse("2011-02-29");
        Executable wrongMonth = () -> LocalDate.parse("2011-02-29");
        Executable wrongDay = () -> LocalDate.parse("2011-10-42");
        asList(wrongLeap, wrongMonth, wrongDay).forEach(exec -> assertThrows(DateTimeException.class, exec));
    }

    @Test
    void plusMinusX_givenLong_shouldReturnRightDate() {
        LocalDate date = LocalDate.of(1996, 12, 24);
        assertThat(date.plusDays(10)).hasToString("1997-01-03");
        assertThat(date.minusDays(30)).hasToString("1996-11-24");
        assertThat(date.plusMonths(2)).hasToString("1997-02-24");
    }

    @Test
    void plusMinus_givenLongAndTemporalUnit_shouldReturnRightDate() {
        LocalDate date = LocalDate.of(2000, 6, 1);
        assertThat(date.plus(1, DAYS)).hasToString("2000-06-02");
        assertThat(date.plus(14, MONTHS)).hasToString("2001-08-01");
        assertThat(date.minus(10, YEARS)).hasToString("1990-06-01");
    }

    @Test
    void plusMinus_givenTemporalAmount_shouldReturnRightDate() {
        assertThat(LocalDate.of(1965, 4, 20).plus(Period.ofYears(5))).isEqualTo(LocalDate.of(1970, 4, 20));
    }

    @Test
    void getDayOfX_givenDate_shouldReturnRightValue() {
        assertThat(LocalDate.of(1986, 8, 17).getDayOfWeek()).isEqualTo(SUNDAY);
        assertThat(LocalDate.of(1789, 1, 12).getDayOfMonth()).isEqualTo(12);
    }

    @Test
    void isLeapYear_givenYear_shouldReturnRightAnswer() {
        assertThat(LocalDate.of(2012, 1, 1).isLeapYear()).isTrue();
        assertThat(LocalDate.of(2011, 1, 1).isLeapYear()).isFalse();
    }

    @Test
    void with_givenTemporalAdjuster_shouldReturnRightDate() {
        LocalDate sourceDate = LocalDate.of(2019, 5, 24); // Friday
        assertThat(sourceDate.with(TemporalAdjusters.lastDayOfMonth())).isEqualTo(LocalDate.of(2019, 5, 31));
        assertThat(sourceDate.with(TemporalAdjusters.firstDayOfMonth())).isEqualTo(LocalDate.of(2019, 5, 1));
        assertThat(sourceDate.with(new NextWorkingDayAdjuster())).isEqualTo(LocalDate.of(2019, 5, 27));
        assertThat(sourceDate.with(TemporalAdjusters.ofDateAdjuster(new NextWorkingDayOperator()))).isEqualTo(
                LocalDate.of(2019, 5, 27));
    }

    @Test
    void isBeforeAfter_givenDate_shouldReturnRightAnswer() {
        assertThat(LocalDate.of(2000, 1, 1).isAfter(LocalDate.of(1999, 12, 31))).isTrue();
        assertThat(LocalDate.of(1910, 2, 2).isBefore(LocalDate.of(2010, 2, 2))).isTrue();
    }

}
