package net.lecigne.codingkatas.ck0031;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@DisplayName("A bookshelf")
class BookShelfSpec {

    private BookShelf shelf;

    @BeforeEach
    void setUp() {
        shelf = new BookShelf();
    }

    @Test
    @DisplayName("is empty when no book is added to it")
    void shelfEmptyWhenNoBookAdded() {
        List<String> books = shelf.books();
        assertTrue(books.isEmpty(), "BookShelf should be empty.");
    }

    @Test
    void shelfContainsTwoBooksWhenTwoBooksAdded() {
        shelf.add("Effective Java", "Code Complete");
        List<String> books = shelf.books();
        assertEquals(2, books.size(), "Bookshelf should have two books.");
    }

    @Test
    void shelfEmptyWhenEmptyContentIsAdded() {
        shelf.add();
        List<String> books = shelf.books();
        assertTrue(books.isEmpty(), "Bookshelf should be empty when adding empty content");
    }

    @Test
    void collectionReturnedFromShelfIsImmutableForClient() {
        shelf.add("Effective Java", "Code Complete");
        List<String> books = shelf.books();
        try {
            books.add("The Mythical Man-Month");
            fail("Book collection should be immutable for the client.");
        } catch (Exception e) {
            assertTrue(e instanceof UnsupportedOperationException, "Bookshelf should throw " +
                    "UnsupportedOperationException");
        }
    }

    @Test
    void shelfArrangedByBookTitle() {
        shelf.add("Effective Java", "Code Complete", "The Mythical Man Month");
        List<String> books = shelf.arrange();
        List<String> expectedBooks = Arrays.asList("Code Complete", "Effective Java", "The Mythical Man Month");
        assertEquals(expectedBooks, books, "Books should be sorted by title");
    }

    @Test
    void booksAreInInsertionOrderAfterArranging() {
        shelf.add("Effective Java", "Code Complete", "The Mythical Man Month");
        shelf.arrange();
        List<String> books = shelf.books();
        List<String> expectedBooks = Arrays.asList("Effective Java", "Code Complete", "The Mythical Man Month");
        assertEquals(expectedBooks, books, "Books should be left in insertion order.");
    }

}
