package org.akira.liquijpa.extension;

import org.akira.extension.PostgreSQLExtension;
import org.akira.liquijpa.entities.AuthorEntity;
import org.akira.liquijpa.entities.BookEntity;
import org.akira.liquijpa.entities.PublisherEntity;
import org.akira.liquijpa.repositories.AuthorRepository;
import org.akira.liquijpa.repositories.BookRepository;
import org.akira.liquijpa.repositories.PublisherRepository;
import org.akira.liquijpa.repositories.TestRep;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(PostgreSQLExtension.class)
@DirtiesContext
public class DBExtendTest {
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    PublisherRepository publisherRepository;
    @Autowired
    TestRep testRep;

    @Test
    void testBook() {
        BookEntity book = createBook();

        bookRepository.save(book);
        Optional<BookEntity> bookSaved = bookRepository.findById(book.getId());

        assertThat(bookSaved).isNotEmpty();
        assertThat(book.getId()).isEqualTo(bookSaved.get().getId());
    }

    private BookEntity createBook() {
        BookEntity book = new BookEntity();

        AuthorEntity authorFirst = new AuthorEntity();
        authorFirst.setId(UUID.fromString("0a4e2e33-3db5-46b9-829b-36025126066b"));
        authorFirst.setName("Шемякин Олег Андреевич");
        authorFirst.setBirthday(LocalDate.of(1989, 11, 9));

        AuthorEntity authorSecond = new AuthorEntity();
        authorSecond.setId(UUID.fromString("2bddc631-41c0-42fc-b0e3-c40c27af14ca"));
        authorSecond.setName("Иванов Иван Иванович");
        authorSecond.setBirthday(LocalDate.of(1989, 11, 9));


        PublisherEntity publisher = new PublisherEntity();
        publisher.setId(UUID.randomUUID());
        publisher.setName("Самиздат");

        book.setId(UUID.randomUUID());
        book.setName("О смысле бытия");
        book.setYear(2024);
        book.setPublisher(publisher);
        book.addAuthor(authorFirst);
        book.addAuthor(authorSecond);

        return book;
    }
}
