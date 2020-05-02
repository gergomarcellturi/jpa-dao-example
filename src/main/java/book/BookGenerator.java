package book;

import book.model.Book;
import com.github.javafaker.Faker;
import java.time.ZoneId;
import java.util.Locale;
import java.util.Optional;

public class BookGenerator {
    private static Faker faker = new Faker(new Locale("en"));

    public Optional<Book> GenerateBook() {

        Book book = new Book();

        book.setIsbn13(faker.code().isbn13());
        book.setAuthor(faker.book().author());
        book.setTitle(faker.book().title());
        book.setFormat(faker.options().option(Book.Format.class));
        book.setPublisher(faker.book().publisher());
        book.setPublicationDate(faker.date().birthday().
                toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        book.setPages(faker.number().numberBetween(40, 1500));
        book.setAvailable(faker.bool().bool());

        return Optional.of(book);
    }
}
