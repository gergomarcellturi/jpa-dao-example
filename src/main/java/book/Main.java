package book;

import book.model.Book;
import com.google.inject.Guice;
import com.google.inject.Injector;
import guice.PersistenceModule;

public class Main {




    public static void main(String[] args) {

        Injector injector = Guice.createInjector(new PersistenceModule("test"));
        BookDao bookDao = injector.getInstance(BookDao.class);
        BookGenerator generator = new BookGenerator();

        for ( int i = 0; i < 1000; i++ ) {
            generator.GenerateBook().ifPresent(bookDao::persist);
        }
        generator.GenerateBook().ifPresent(book -> {
            book.setIsbn13("TEST");
            bookDao.persist(book);
        });

        bookDao.findAll().stream().forEach(System.out::println);
        System.out.println("Finding by Isbn13...");
        bookDao.findByIsbn13("TEST").stream().forEach(System.out::println);

    }
}
