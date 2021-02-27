package net.lecigne.codingkatas.ck0032;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.BDDAssertions.then;

@DisplayName("A book filter")
@ExtendWith(BooksParameterResolver.class)
class BookFilterSpec {
    @Nested
    @DisplayName("should filter books by publication year")
    class BookPublishedAfterFilterSpec {
        @Test
        @DisplayName("-> when filtering books published after a given year should match the correct books")
        void bookFilter_whenFilteringBooksAfterGivenYear_shouldMatchCorrectBooks(Map<String, Book> books) {
            List<Book> actualBooks = books.values().stream()
                    .filter(BookPublicationYearFilter.after(2007))
                    .collect(Collectors.toList());
            then(actualBooks).as("Books after 2007 should contain 2 books: Effective Java and Clean Code")
                    .hasSize(2)
                    .containsOnly(books.get("Effective Java"), books.get("Clean Code"));
        }

        @Test
        @DisplayName("-> when filtering books published before a given year should match the correct books")
        void bookFilter_whenFilteringBooksBeforeGivenYear_shouldMatchCorrectBooks(Map<String, Book> books) {
            List<Book> actualBooks = books.values().stream()
                    .filter(BookPublicationYearFilter.before(2000))
                    .collect(Collectors.toList());
            then(actualBooks).as("Books before 2000 should contain 1 book: The Mythical Man-Month")
                    .hasSize(1)
                    .containsOnly(books.get("The Mythical Man-Month"));
        }
    }
}
