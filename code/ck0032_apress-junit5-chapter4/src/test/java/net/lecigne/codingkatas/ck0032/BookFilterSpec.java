package net.lecigne.codingkatas.ck0032;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

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

    @Nested
    @DisplayName("should filter books with a composite filter")
    class CompositeFilterSpec {
        @Test
        @DisplayName("-> when filtering books with composite filter should not invoke after first failure")
        void bookFilter_whenFilteringBooksWithCompositeFilter_shouldNotInvokeAfterFirstFailure(
                Map<String, Book> books) {
            CompositeFilter compositeFilter = new CompositeFilter();
            BookFilter invokedMockedFilter = Mockito.mock(BookFilter.class);
            given(invokedMockedFilter.test(any())).willReturn(false);
            compositeFilter.addFilter(invokedMockedFilter);

            BookFilter nonInvokedMockedFilter = Mockito.mock(BookFilter.class);
            given(nonInvokedMockedFilter.test(any())).willReturn(true);
            compositeFilter.addFilter(nonInvokedMockedFilter);

            then(compositeFilter.test(books.get("Clean Code")))
                    .as("No book should be matched by composite filter with a false filter")
                    .isFalse();
            verify(invokedMockedFilter, times(1)).test(any());
            verifyNoInteractions(nonInvokedMockedFilter);
        }

        @Test
        @DisplayName("-> when filtering books with composite filter should invoke all filters")
        void bookFilter_whenFilteringBooksWithCompositeFilter_shouldInvokeAllFilters(Map<String, Book> books) {
            Book cleanCode = books.get("Clean Code");
            CompositeFilter compositeFilter = new CompositeFilter();
            BookFilter invokedMockedFilter1 = Mockito.mock(BookFilter.class);
            given(invokedMockedFilter1.test(any())).willReturn(true);
            compositeFilter.addFilter(invokedMockedFilter1);

            BookFilter invokedMockedFilter2 = Mockito.mock(BookFilter.class);
            given(invokedMockedFilter2.test(any())).willReturn(true);
            compositeFilter.addFilter(invokedMockedFilter2);

            then(compositeFilter.test(cleanCode))
                    .as("Every book should be matched by composite filter with only true filters")
                    .isTrue();
            verify(invokedMockedFilter1, times(1)).test(cleanCode);
            verify(invokedMockedFilter2, times(1)).test(cleanCode);
        }
    }
}
