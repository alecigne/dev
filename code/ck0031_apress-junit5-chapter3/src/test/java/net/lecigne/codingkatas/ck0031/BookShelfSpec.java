package net.lecigne.codingkatas.ck0031;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

}
