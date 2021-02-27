package net.lecigne.codingkatas.ck0032;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Map;

import static org.assertj.core.api.BDDAssertions.then;

@DisplayName("A bookshelf progress")
@ExtendWith(BooksParameterResolver.class)
class BookShelfProgressSpec {
    private BookShelf shelf;
    private Book effectiveJava;
    private Book codeComplete;
    private Book mythicalManMonth;
    private Book cleanCode;
    private Book refactoring;

    @BeforeEach
    void setUp(Map<String, Book> books) {
        shelf = new BookShelf();
        effectiveJava = books.get("Effective Java");
        codeComplete = books.get("Code Complete");
        mythicalManMonth = books.get("The Mythical Man-Month");
        cleanCode = books.get("Clean Code");
        refactoring = books.get("Refactoring: Improving the Design of Existing Code");
        shelf.add(effectiveJava, codeComplete, mythicalManMonth, cleanCode, refactoring);
    }

    @Test
    @DisplayName("when no book has been started should be 0%")
    void bookshelfProgress_whenNoBookStarted_shouldBeZeroPercent() {
        Progress progress = shelf.progress();
        then(progress.toRead()).as("All books should have the To-Do progress state")
                .isEqualTo(100);
        then(progress.inProgress()).as("No book should be started")
                .isZero();
        then(progress.completed()).as("No book should be completed")
                .isZero();
    }
}
