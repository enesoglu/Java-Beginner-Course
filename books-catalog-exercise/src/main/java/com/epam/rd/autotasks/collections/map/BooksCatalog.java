package com.epam.rd.autotasks.collections.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Locale;

public class BooksCatalog {
    private static final String EOL = "\n";
    private Map<Author, List<Book>> catalog;

    public BooksCatalog() {
        this.catalog = new TreeMap<>();
    }

    public BooksCatalog(Map<Author, List<Book>> catalog) {
        if (catalog == null) {
            throw new NullPointerException();
        }
        this.catalog = new TreeMap<>(catalog);
    }

    /**
     * Returns a List of books of the specified author.
     *
     * @param author the author of books to search for.
     * @return a list of books or {@code null}
     * if there is no such author in the catalog.
     */
    public List<Book> findByAuthor(Author author) {
        if (author == null) {
            throw new NullPointerException();
        }
        return catalog.get(author);
    }

    /**
     * @return the string representation of all authors
     * separated by the current operating system {@code lineSeparator}.
     */
    public String getAllAuthors() {
        StringBuilder sb = new StringBuilder();
        Set<Author> authors = catalog.keySet();
        boolean first = true;
        for (Author author : authors) {
            if (!first) {
                sb.append(EOL);
            }
            sb.append(author.toString());
            first = false;
        }
        return sb.toString();
    }

    /**
     * Searches for pairs of (author, book) by the book title.
     * The pair must be included in the resulting map if the
     * book title contains the specified string matched ignore case.
     * All authors of the book must be specified in the
     * book authors list.
     *
     * @param pattern the string to search for in the book title.
     * @return the map which contains all found books and their authors.
     * It must be sorted by titles of books, if the titles match,
     * by increasing cost.
     */
    public Map<Book, List<Author>> findAuthorsByBookTitle(String pattern) {
        if (pattern == null) {
            throw new NullPointerException();
        }

        Map<Book, List<Author>> result = new TreeMap<>();
        String lowerPattern = pattern.toLowerCase(Locale.ROOT);

        for (Map.Entry<Author, List<Book>> entry : catalog.entrySet()) {
            Author author = entry.getKey();
            List<Book> books = entry.getValue();

            for (Book book : books) {
                if (book.getTitle().toLowerCase(Locale.ROOT).contains(lowerPattern)) {
                    List<Author> authorsForBook = result.get(book);
                    if (authorsForBook == null) {
                        authorsForBook = new ArrayList<>();
                        result.put(book, authorsForBook);
                    }
                    if (!authorsForBook.contains(author)) {
                        authorsForBook.add(author);
                    }
                }
            }
        }
        return result;
    }

    /**
     * Searches for all books whose genre list contains the specified string.
     * The book must be included in the resulting list if at least
     * one genre of the book contains the specified pattern ignoring case.
     *
     * @param pattern the string to search for in the book genre list.
     * @return a set of books sorted using natural ordering.
     * @see Book class.
     */
    public Set<Book> findBooksByGenre(String pattern) {
        if (pattern == null) {
            throw new NullPointerException();
        }

        Set<Book> result = new TreeSet<>();
        String lowerPattern = pattern.toLowerCase(Locale.ROOT);

        for (List<Book> books : catalog.values()) {
            for (Book book : books) {
                for (String genre : book.getGenres()) {
                    if (genre.toLowerCase(Locale.ROOT).contains(lowerPattern)) {
                        result.add(book);
                        break;
                    }
                }
            }
        }

        if (result.isEmpty()) {
            return null;
        }
        return result;
    }

    /**
     * Searches for authors of the specified book.
     *
     * @param book the book.
     * @return a list of authors of the specified book.
     * @throws NullPointerException if the parameter is {@code null}
     */
    public List<Author> findAuthorsByBook(Book book) {
        if (book == null) {
            throw new NullPointerException();
        }

        List<Author> result = new ArrayList<>();

        for (Map.Entry<Author, List<Book>> entry : catalog.entrySet()) {
            List<Book> books = entry.getValue();
            if (books.contains(book)) {
                result.add(entry.getKey());
            }
        }

        if (result.isEmpty()) {
            return result;
        }

        Collections.sort(result);
        return result;
    }

    @Override
    public String toString() {
        return catalog.toString();
    }
}