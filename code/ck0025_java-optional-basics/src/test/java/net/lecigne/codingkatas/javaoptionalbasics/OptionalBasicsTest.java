package net.lecigne.codingkatas.javaoptionalbasics;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * An Optional tutorial in the form of unit tests.
 *
 * @author Anthony Le Cigne
 */
class OptionalBasicsTest {

    @Test
    void empty_shouldCreateEmptyOptional() {
        Optional<String> opt = Optional.empty();
        assertThat(opt).isEmpty();
    }

    @Test
    void of_givenNonNull_shouldCreateNonEmptyOptional() {
        Optional<String> opt = Optional.of("foobar");
        assertThat(opt).isPresent();
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    void of_givenNull_shouldThrowException() {
        assertThatThrownBy(() -> Optional.of(null)).isInstanceOf(NullPointerException.class);
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    void ofNullable_givenNonNull_shouldCreateNonEmptyOptional() {
        Optional<String> opt = Optional.ofNullable("foobar");
        assertThat(opt).isPresent();
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    void ofNullable_givenNull_shouldCreateEmptyOptional() {
        Optional<String> opt = Optional.ofNullable(null);
        assertThat(opt).isEmpty();
    }

}
