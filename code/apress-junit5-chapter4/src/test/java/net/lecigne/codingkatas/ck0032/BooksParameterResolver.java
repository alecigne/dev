package net.lecigne.codingkatas.ck0032;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.lang.reflect.Parameter;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class BooksParameterResolver implements ParameterResolver {

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        Parameter parameter = parameterContext.getParameter();
        return Objects.equals(parameter.getParameterizedType().getTypeName(),
                "java.util.Map<java.lang.String, net.lecigne.codingkatas.ck0032.Book>");
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        ExtensionContext.Store store = extensionContext.getStore(ExtensionContext.Namespace.create(Book.class));
        return store.getOrComputeIfAbsent("books", k -> getBooks());
    }

    private Map<String, Book> getBooks() {
        Map<String, Book> books = new HashMap<>();
        Book effectiveJava = new Book.Builder()
                .author("Joshua Bloch").title("Effective Java")
                .publishedOn(LocalDate.of(2008, Month.MAY, 8))
                .build();
        Book codeComplete = new Book.Builder()
                .author("Steve McConnell").title("Code Complete")
                .publishedOn(LocalDate.of(2004, Month.JUNE, 9))
                .build();
        Book mythicalManMonth = new Book.Builder()
                .author("Frederick Phillips Brooks").title("The Mythical Man-Month")
                .publishedOn(LocalDate.of(1975, Month.JANUARY, 1))
                .build();
        Book cleanCode = new Book.Builder()
                .author("Robert Cecil Martin").title("Clean Code")
                .publishedOn(LocalDate.of(2008, Month.AUGUST, 1))
                .build();
        Book refactoring = new Book.Builder()
                .author("Martin Fowler").title("Refactoring: Improving the Design of Existing Code")
                .publishedOn(LocalDate.of(2002, Month.MARCH, 9))
                .build();
        books.put(effectiveJava.getTitle(), effectiveJava);
        books.put(codeComplete.getTitle(), codeComplete);
        books.put(mythicalManMonth.getTitle(), mythicalManMonth);
        books.put(cleanCode.getTitle(), cleanCode);
        books.put(refactoring.getTitle(), refactoring);
        return books;
    }

}
