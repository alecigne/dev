package net.lecigne.codingkatas.ck0031;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@DisplayName("A bookshelf")
class BookShelfSpec {

    private BookShelf shelf;
    private Book effectiveJava;
    private Book codeComplete;
    private Book mythicalManMonth;

    @BeforeEach
    void setUp() {
        shelf = new BookShelf();
        effectiveJava = new Book.Builder()
                .title("Effective Java")
                .author("Joshua Bloch")
                .publishedOn(LocalDate.of(2008, Month.MAY, 8))
                .build();
        codeComplete = new Book.Builder()
                .title("Code Complete")
                .author("Steve McConnel")
                .publishedOn(LocalDate.of(2004, Month.JUNE, 9))
                .build();
        mythicalManMonth = new Book.Builder()
                .title("The Mythical Man-Month")
                .author("Frederick Phillips Brooks")
                .publishedOn(LocalDate.of(1975, Month.JANUARY, 1))
                .build();
    }

    @Test
    @DisplayName("is empty when no book is added to it")
    void shelfEmptyWhenNoBookAdded() {
        List<Book> books = shelf.books();
        assertTrue(books.isEmpty(), "BookShelf should be empty.");
    }

    @Test
    void shelfContainsTwoBooksWhenTwoBooksAdded() {
        shelf.add(effectiveJava, codeComplete);
        List<Book> books = shelf.books();
        assertEquals(2, books.size(), "Bookshelf should have two books.");
    }

    @Test
    void shelfEmptyWhenEmptyContentIsAdded() {
        shelf.add();
        List<Book> books = shelf.books();
        assertTrue(books.isEmpty(), "Bookshelf should be empty when adding empty content");
    }

    @Test
    void collectionReturnedFromShelfIsImmutableForClient() {
        shelf.add(effectiveJava, codeComplete);
        List<Book> books = shelf.books();
        try {
            books.add(mythicalManMonth);
            fail("Book collection should be immutable for the client.");
        } catch (Exception e) {
            assertTrue(e instanceof UnsupportedOperationException, "Bookshelf should throw " +
                    "UnsupportedOperationException");
        }
    }

    @Test
    void shelfArrangedByBookTitle() {
        shelf.add(effectiveJava, codeComplete, mythicalManMonth);
        List<Book> books = shelf.arrange();
        List<Book> expectedBooks = Arrays.asList(codeComplete, effectiveJava, mythicalManMonth);
        assertEquals(expectedBooks, books, "Books should be sorted by title");
    }

    @Test
    void booksAreInInsertionOrderAfterArranging() {
        shelf.add(effectiveJava, codeComplete, mythicalManMonth);
        shelf.arrange();
        List<Book> books = shelf.books();
        List<Book> expectedBooks = Arrays.asList(effectiveJava, codeComplete, mythicalManMonth);
        assertEquals(expectedBooks, books, "Books should be left in insertion order.");
    }

    @Test
    void shelfArrangedByReverseAlphabeticalOrder() {
        shelf.add(effectiveJava, codeComplete, mythicalManMonth);
        Comparator<Book> comparator = Comparator.<Book>naturalOrder().reversed();
        List<Book> actualBooks = shelf.arrange(comparator);
        then(actualBooks)
                .as("Books should be sorted by reverse alphabetical order")
                .isNotEmpty()
                .isSortedAccordingTo(comparator);
    }

    @Test
    void shelfArrangedByPublicationDate() {
        shelf.add(codeComplete, effectiveJava, mythicalManMonth);
        Comparator<Book> comparator = Comparator.comparing(Book::getPublishedOn);
        List<Book> actualBooks = shelf.arrange(comparator);
        then(actualBooks)
                .as("Books should be arranged in chronological order")
                .isNotEmpty()
                .isSortedAccordingTo(comparator);
    }

}
