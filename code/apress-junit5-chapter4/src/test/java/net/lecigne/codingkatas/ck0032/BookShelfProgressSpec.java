package net.lecigne.codingkatas.ck0032;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

import static java.time.Month.AUGUST;
import static java.time.Month.JULY;
import static org.assertj.core.api.BDDAssertions.then;

@DisplayName("A bookshelf progress")
@ExtendWith(BooksParameterResolver.class)
class BookShelfProgressSpec {
    private BookShelf shelf;

    @BeforeEach
    void setUp(Map<String, Book> books) {
        shelf = new BookShelf();
        shelf.add(books.values().toArray(new Book[0]));
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

    @Test
    @DisplayName("when a few books have been completed should be correct")
    void bookshelfProgress_whenAFewBooksCompleted_shouldBeCorrect(Map<String, Book> books) {
        books.get("Effective Java").startedReadingOn(LocalDate.of(2016, JULY, 1));
        books.get("Effective Java").finishedReadingOn(LocalDate.of(2016, JULY, 31));
        books.get("Clean Code").startedReadingOn(LocalDate.of(2016, AUGUST, 1));
        books.get("Clean Code").finishedReadingOn(LocalDate.of(2016, AUGUST, 31));
        Progress progress = shelf.progress();
        then(progress.toRead()).as("60% of books should have the To-Do progress state")
                .isEqualTo(60);
        then(progress.inProgress()).as("No book should be started")
                .isZero();
        then(progress.completed()).as("40% of books should be completed")
                .isEqualTo(40);
    }

    @Test
    @DisplayName("when books are in all progress states should be correct")
    void bookshelfProgress_whenBooksInAllProgressStates_shouldBeCorrect(Map<String, Book> books) {
        books.get("Effective Java").startedReadingOn(LocalDate.of(2016, JULY, 1));
        books.get("Effective Java").finishedReadingOn(LocalDate.of(2016, JULY, 31));
        books.get("Clean Code").startedReadingOn(LocalDate.of(2016, AUGUST, 1));
        Progress progress = shelf.progress();
        then(progress.toRead()).as("60% of books should have the To-Do progress state")
                .isEqualTo(60);
        then(progress.inProgress()).as("20% of books should be started")
                .isEqualTo(20);
        then(progress.completed()).as("20% of books should be completed")
                .isEqualTo(20);
    }

}
