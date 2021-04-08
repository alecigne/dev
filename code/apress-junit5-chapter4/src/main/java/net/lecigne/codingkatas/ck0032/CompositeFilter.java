package net.lecigne.codingkatas.ck0032;

import java.util.ArrayList;
import java.util.List;

public class CompositeFilter implements BookFilter {
    private final List<BookFilter> bookFilters;

    public CompositeFilter() {
        this.bookFilters = new ArrayList<>();
    }

    public void addFilter(BookFilter bookFilter) {
        bookFilters.add(bookFilter);
    }

    @Override
    public boolean test(Book book) {
        return bookFilters.stream().allMatch(filter -> filter.test(book));
    }
}
