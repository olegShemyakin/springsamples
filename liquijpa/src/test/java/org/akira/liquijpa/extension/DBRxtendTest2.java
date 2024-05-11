package org.akira.liquijpa.extension;

import org.akira.extension.PostgreSQLExtension;
import org.akira.liquijpa.entities.AuthorEntity;
import org.akira.liquijpa.entities.BookEntity;
import org.akira.liquijpa.entities.PublisherEntity;
import org.akira.liquijpa.repositories.AuthorRepository;
import org.akira.liquijpa.repositories.BookRepository;
import org.akira.liquijpa.repositories.PublisherRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(PostgreSQLExtension.class)
@DirtiesContext
public class DBRxtendTest2 {
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    PublisherRepository publisherRepository;

    @Test
    void testBook() {
        BookEntity book = createBook();

        bookRepository.save(book);

        List<BookEntity> books = bookRepository.findAll();

        assertThat(books).isNotEmpty();
        assertThat(books).hasSize(1);
    }

    private BookEntity createBook() {
        BookEntity book = new BookEntity();

        AuthorEntity author = new AuthorEntity();
        author.setId(UUID.randomUUID());
        author.setName("Шемякин Олег Андреевич");
        author.setBirthday(LocalDate.of(1989, 11, 9));


        PublisherEntity publisher = new PublisherEntity();
        publisher.setId(UUID.randomUUID());
        publisher.setName("Самиздат");

        book.setId(UUID.randomUUID());
        book.setName("О смысле бытия");
        book.setYear(2024);
        book.setPublisher(publisher);
        book.addAuthor(author);

        return book;
    }
}
