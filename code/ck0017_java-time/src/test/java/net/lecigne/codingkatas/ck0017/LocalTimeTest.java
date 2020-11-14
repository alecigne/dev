package net.lecigne.codingkatas.ck0017;

import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.DateTimeException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoField;

import static java.time.temporal.ChronoUnit.MINUTES;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * A LocalTime tutorial in the form of unit tests.
 *
 * @author Anthony Le Cigne
 */
class LocalTimeTest {

    @Test
    void now_givenClock_shouldReturnRightDate() {
        String expectedTime = "22:15:36";
        Clock clock = Clock.fixed(Instant.parse("2016-07-01T" + expectedTime + ".00Z"), ZoneId.of("UTC"));
        assertThat(LocalTime.now(clock)).hasToString(expectedTime);
    }

    @Test
    void of_givenTime_shouldReturnRightTime() {
        assertThat(LocalTime.of(6, 30)).hasToString("06:30");
    }

    @Test
    void of_givenWrongTime_shouldThrowException() {
        assertThatThrownBy(() -> LocalTime.of(25, 12)).isInstanceOf(DateTimeException.class);
        assertThatThrownBy(() -> LocalTime.of(4, 60)).isInstanceOf(DateTimeException.class);
        assertThatThrownBy(() -> LocalTime.of(12, 18, 75)).isInstanceOf(DateTimeException.class);
    }

    @Test
    void parse_givenFormattedTime_shouldReturnRightTime() {
        String time = "07:27";
        String timeSeconds = "07:27:24";
        assertThat(LocalTime.parse(time)).hasToString(time);
        assertThat(LocalTime.parse(timeSeconds)).hasToString(timeSeconds);
    }

    @Test
    void parse_givenWrongTime_shouldThrowException() {
        assertThatThrownBy(() -> LocalTime.parse("25:12")).isInstanceOf(DateTimeException.class);
        assertThatThrownBy(() -> LocalTime.parse("4:60")).isInstanceOf(DateTimeException.class);
        assertThatThrownBy(() -> LocalTime.parse("12:18:75")).isInstanceOf(DateTimeException.class);
    }

    @Test
    void plusMinusX_givenLong_shouldReturnRightTime() {
        assertThat(LocalTime.of(13, 9, 59).plusMinutes(1)).isEqualTo(LocalTime.of(13, 10, 59));
    }

    @Test
    void plusMinusX_givenLongAndTemporalUnit_shouldReturnRightTime() {
        assertThat(LocalTime.of(0, 12, 30).plus(1441, MINUTES)).isEqualTo(LocalTime.of(0, 13, 30));
    }

    @Test
    void plusMinusX_givenTemporalAmount_shouldReturnRightTime() {
        assertThat(LocalTime.of(14, 10, 12).plus(Duration.ofSeconds(8))).isEqualTo(LocalTime.of(14, 10, 20));
    }

    @Test
    void getX_givenTime_shouldReturnRightValue() {
        assertThat(LocalTime.of(23, 0, 0).getHour()).isEqualTo(23);
        assertThat(LocalTime.of(21, 27, 45).getMinute()).isEqualTo(27);
        assertThat(LocalTime.of(22, 1, 4).get(ChronoField.CLOCK_HOUR_OF_AMPM)).isEqualTo(10);
    }

    @Test
    void isBeforeAfter_givenTime_shouldReturnRightAnswer() {
        assertThat(LocalTime.of(14, 10).isAfter(LocalTime.of(10, 10))).isTrue();
    }

}
