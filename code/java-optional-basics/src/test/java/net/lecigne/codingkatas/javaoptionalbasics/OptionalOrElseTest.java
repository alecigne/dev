package net.lecigne.codingkatas.javaoptionalbasics;

import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * An Optional tutorial in the form of unit tests.
 *
 * @author Anthony Le Cigne
 */
class OptionalOrElseTest {

    @Test
    void orElse_onNonEmptyOptional_shouldReturnValue() {
        Optional<String> maybeString = Optional.of("foobar");
        String strActual = maybeString.orElse("DEFAULT");
        assertThat(strActual).isEqualTo("foobar");
    }

    @Test
    void orElse_onEmptyOptional_shouldReturnAlternativeValue() {
        Optional<String> maybeString = Optional.empty();
        String strActual = maybeString.orElse("DEFAULT");
        assertThat(strActual).isEqualTo("DEFAULT");
    }

    @Test
    void orElse_onEmptyOptional_shouldEvalMethod() {
        Optional<String> maybeString = Optional.empty();
        String strActual = maybeString.orElse(getDefault());
        assertThat(strActual).isEqualTo("DEFAULT");
    }

    @Test
    void orElseGet_onNonEmptyOptional_shouldReturnValue() {
        Optional<String> maybeString = Optional.of("foobar");
        Supplier<String> supplier = () -> "DEFAULT";
        String strActual = maybeString.orElseGet(supplier);
        assertThat(strActual).isEqualTo("foobar");
    }

    @Test
    void orElseGet_onEmptyOptional_shouldReturnAlternativeValue() {
        Optional<String> maybeString = Optional.empty();
        Supplier<String> supplier = () -> "DEFAULT";
        String strActual = maybeString.orElseGet(supplier);
        assertThat(strActual).isEqualTo("DEFAULT");
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    void orElseThrow_onEmptyOptional_shouldThrowException() {
        Optional<String> maybeString = Optional.empty();
        assertThatThrownBy(() -> maybeString.orElseThrow(Exception::new)).isInstanceOf(Exception.class);
    }

    private String getDefault() {
        return "DEFAULT";
    }

}
