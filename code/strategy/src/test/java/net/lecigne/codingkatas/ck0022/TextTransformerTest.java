package net.lecigne.codingkatas.ck0022;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TextTransformerTest {

    @Test
    void transform_shouldReverse() {
        var transformer = new TextTransformer(TextTransformation.reverse());
        assertThat(transformer.transform("foobar")).isEqualTo("raboof");
    }

    @Test
    void transform_shouldCapitalize() {
        var transformer = new TextTransformer(TextTransformation.uppercase());
        assertThat(transformer.transform("foobar")).isEqualTo("FOOBAR");
    }

    @Test
    void transform_shouldCombine() {
        var transformer = new TextTransformer(TextTransformation.reverse(), TextTransformation.uppercase());
        assertThat(transformer.transform("foobar")).isEqualTo("RABOOF");
    }
}
