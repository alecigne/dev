package net.lecigne.codingkatas.ck0032;

import java.time.Year;
import java.util.function.Predicate;

public final class BookPublicationYearFilter implements BookFilter {

    private Predicate<Year> yearPredicate;

    private BookPublicationYearFilter() {
    }

    static BookPublicationYearFilter before(int year) {
        return getFilterWithPredicate(Year.of(year)::isAfter);
    }

    static BookPublicationYearFilter after(int year) {
        return getFilterWithPredicate(Year.of(year)::isBefore);
    }

    private static BookPublicationYearFilter getFilterWithPredicate(Predicate<Year> p) {
        BookPublicationYearFilter filter = new BookPublicationYearFilter();
        filter.yearPredicate = p;
        return filter;
    }

    @Override
    public boolean test(Book book) {
        return book != null && yearPredicate.test(Year.of(book.getPublishedOn().getYear()));
    }

}
