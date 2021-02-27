package net.lecigne.codingkatas.ck0032;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static java.time.Month.AUGUST;
import static org.assertj.core.api.BDDAssertions.then;

@DisplayName("Bookshelf search")
@ExtendWith(BooksParameterResolver.class)
class BookShelfSearchSpec {
    private BookShelf shelf;

    @BeforeEach
    void init(Map<String, Book> books) {
        shelf = new BookShelf();
        shelf.add(books.values().toArray(new Book[0]));
    }

    @Test
    @DisplayName("when searching by title should return the correct books")
    void bookshelfSearch_whenSearchingByTitle_shouldReturnCorrectBooks() {
        List<Book> actualBooks = shelf.findBooksByTitle("code");
        then(actualBooks).as("Books should contain titles containing \"code\"")
                .hasSize(3);
    }

    @Test
    @DisplayName("when searching by title and hint should return the correct books")
    void bookshelfSearch_whenSearchingByTitleAndHint_shouldReturnCorrectBooks() {
        BookFilter predicate = book -> book.getPublishedOn().isBefore(LocalDate.of(2004, AUGUST, 1));
        List<Book> actualBooks = shelf.findBooksByTitle("code", predicate);
        then(actualBooks).as("Books should contain titles containing \"code\"")
                .hasSize(2);
    }
}
