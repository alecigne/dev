package net.lecigne.codingkatas.javaoptionalbasics;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class OptionalFilterTest {

    @Test
    void filter_givenPredicate_shouldReturnCorrectOptional() {
        LocalDate referenceDate = LocalDate.of(2018, 4, 26);
        Optional<LocalDate> maybeDate1 = Optional.of(LocalDate.of(2022, 5, 29));
        Optional<LocalDate> maybeDate2 = Optional.of(LocalDate.of(2017, 3, 1));
        assertThat(maybeDate1.filter(date -> date.isAfter(referenceDate))).isPresent();
        assertThat(maybeDate2.filter(date -> date.isAfter(referenceDate))).isEmpty();
    }

}
