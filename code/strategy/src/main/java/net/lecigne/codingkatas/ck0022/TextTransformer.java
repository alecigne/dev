package net.lecigne.codingkatas.ck0022;

import java.util.stream.Stream;

public class TextTransformer {

    private final TextTransformation textTransformation;

    public TextTransformer(TextTransformation... textTransformations) {
        this.textTransformation = Stream.of(textTransformations).reduce(text -> text, TextTransformation::combine);
    }

    public String transform(String text) {
        return textTransformation.apply(text);
    }

}
