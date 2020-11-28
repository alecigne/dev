package net.lecigne.codingkatas.ck0022;

import java.util.function.UnaryOperator;

public interface TextTransformation extends UnaryOperator<String> {

    static TextTransformation reverse() {
        return text -> new StringBuilder(text).reverse().toString();
    }

    static TextTransformation uppercase() {
        return String::toUpperCase;
    }

    default TextTransformation combine(TextTransformation after) {
        return text -> after.apply(this.apply(text));
    }

}
