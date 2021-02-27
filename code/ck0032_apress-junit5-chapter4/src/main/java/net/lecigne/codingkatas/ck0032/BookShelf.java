package net.lecigne.codingkatas.ck0032;

import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BookShelf {

    private final List<Book> books = new ArrayList<>();

    public List<Book> books() {
        return Collections.unmodifiableList(books);
    }

    public void add(Book... bookToAdd) {
        books.addAll(Arrays.asList(bookToAdd));
    }

    public List<Book> arrange() {
        return arrange(Comparator.naturalOrder());
    }

    public List<Book> arrange(Comparator<Book> criteria) {
        return books.stream().sorted(criteria).collect(Collectors.toList());
    }

    public Map<Year, List<Book>> groupByPublicationYear() {
        return groupBy(book -> Year.of(book.getPublishedOn().getYear()));
    }

    public <K> Map<K, List<Book>> groupBy(Function<Book, K> function) {
        return books.stream().collect(Collectors.groupingBy(function));
    }

    public Progress progress() {
        int booksRead = Math.toIntExact(books.stream().filter(Book::isRead).count());
        int booksInProgress = Math.toIntExact(books.stream().filter(Book::isInProgress).count());
        int booksToRead = books.size() - booksRead - booksInProgress;
        int percentageCompleted = booksRead * 100 / books.size();
        int percentageInProgress = booksInProgress * 100 / books.size();
        int percentageToRead = booksToRead * 100 / books.size();
        return new Progress(percentageToRead, percentageInProgress, percentageCompleted);
    }
}
