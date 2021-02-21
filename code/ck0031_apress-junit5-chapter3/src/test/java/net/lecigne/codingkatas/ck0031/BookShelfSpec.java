package net.lecigne.codingkatas.ck0031;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

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
    private Book cleanCode;

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
        cleanCode = new Book.Builder()
                .title("Clean Code")
                .author("Robert Cecil Martin")
                .publishedOn(LocalDate.of(2008, Month.AUGUST, 1))
                .build();
    }

    @Test
    @DisplayName("when no book is added should be empty")
    void bookshelf_whenNoBookAdded_shouldBeEmpty() {
        List<Book> books = shelf.books();
        assertTrue(books.isEmpty(), "BookShelf should be empty.");
    }

    @Test
    @DisplayName("when n books are added should contain n books")
    void bookshelf_whenNBooksAdded_shouldContainNBooks() {
        shelf.add(effectiveJava, codeComplete);
        List<Book> books = shelf.books();
        assertEquals(2, books.size(), "Bookshelf should have two books.");
    }

    @Test
    @DisplayName("when an empty list of books is added should be empty")
    void bookshelf_whenEmptyListAdded_shouldBeEmpty() {
        shelf.add();
        List<Book> books = shelf.books();
        assertTrue(books.isEmpty(), "Bookshelf should be empty when adding empty content");
    }

    @Test
    @DisplayName("when modified in place should throw UnsupportedOperationException")
    void bookshelf_whenModifiedInPlace_shouldThrowUnsupportedOperationException() {
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
    @DisplayName("when fetched with default sorting should be sorted by title")
    void bookshelf_whenFetchedWithDefaultSorting_shouldBeSortedByTitle() {
        shelf.add(effectiveJava, codeComplete, mythicalManMonth);
        List<Book> books = shelf.arrange();
        List<Book> expectedBooks = Arrays.asList(codeComplete, effectiveJava, mythicalManMonth);
        assertEquals(expectedBooks, books, "Books should be sorted by title");
    }

    @Test
    @DisplayName("when fetched with any sorting should stay in insertion order")
    void bookshelf_whenFetchedWithAnySorting_shouldStayInInsertionOrder() {
        shelf.add(effectiveJava, codeComplete, mythicalManMonth);
        shelf.arrange();
        List<Book> books = shelf.books();
        List<Book> expectedBooks = Arrays.asList(effectiveJava, codeComplete, mythicalManMonth);
        assertEquals(expectedBooks, books, "Books should be left in insertion order.");
    }

    @Test
    @DisplayName("when fetched with reverse alphabetical sorting should be sorted correctly")
    void bookshelf_whenFetchedWithReverseAlphabeticalSorting_shouldBeSortedCorrectly() {
        shelf.add(effectiveJava, codeComplete, mythicalManMonth);
        Comparator<Book> comparator = Comparator.<Book>naturalOrder().reversed();
        List<Book> actualBooks = shelf.arrange(comparator);
        then(actualBooks)
                .as("Books should be sorted by reverse alphabetical order")
                .isNotEmpty()
                .isSortedAccordingTo(comparator);
    }

    @Test
    @DisplayName("when fetched with sorting by publication date should be sorted correctly")
    void bookshelf_whenFetchedWithSortingByPublicationDate_shouldBeSortedCorrectly() {
        shelf.add(codeComplete, effectiveJava, mythicalManMonth);
        Comparator<Book> comparator = Comparator.comparing(Book::getPublishedOn);
        List<Book> actualBooks = shelf.arrange(comparator);
        then(actualBooks)
                .as("Books should be arranged in chronological order")
                .isNotEmpty()
                .isSortedAccordingTo(comparator);
    }

    @Test
    @DisplayName("when fetched with default grouping should group correctly")
    void bookshelf_whenFetchedWithDefaultGrouping_shouldGroupCorrectly() {
        shelf.add(effectiveJava, codeComplete, mythicalManMonth, cleanCode);
        Map<Year, List<Book>> booksByYear = shelf.groupByPublicationYear();
        then(booksByYear).as("Books should be grouped correctly by year")
                .isNotEmpty().hasSize(3)
                .containsEntry(Year.of(2008), Arrays.asList(effectiveJava, cleanCode))
                .containsEntry(Year.of(2004), Collections.singletonList(codeComplete))
                .containsEntry(Year.of(1975), Collections.singletonList(mythicalManMonth));
    }

}
