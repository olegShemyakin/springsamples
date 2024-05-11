package org.akira.mybatisjpa;

import org.akira.extension.PostgreSQLExtension;
import org.akira.mybatisjpa.dto.mybatis.Book;
import org.akira.mybatisjpa.entities.AuthorEntity;
import org.akira.mybatisjpa.entities.BookEntity;
import org.akira.mybatisjpa.entities.PublisherEntity;
import org.akira.mybatisjpa.repositories.BookRepository;
import org.akira.mybatisjpa.repositories.mybatis.BookRepositoryBatis;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(PostgreSQLExtension.class)
@DirtiesContext
public class DBExtendTest {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    BookRepositoryBatis bookRepositoryBatisMybatis;

    @Test
    void testBook() {
        BookEntity book = createBook();

        bookRepository.save(book);
        Optional<BookEntity> bookSaved = bookRepository.findById(book.getId());

        List<Book> books = bookRepositoryBatisMybatis.findAll();

        assertThat(bookSaved).isNotEmpty();
        assertThat(book.getId()).isEqualTo(bookSaved.get().getId());
    }

    private BookEntity createBook() {

        AuthorEntity authorFirst = new AuthorEntity();
        authorFirst.setName("Шемякин Олег Андреевич");
        authorFirst.setBirthday(LocalDate.of(1989, 11, 9));

        AuthorEntity authorSecond = new AuthorEntity();
        authorSecond.setName("Иванов Иван Иванович");
        authorSecond.setBirthday(LocalDate.of(1989, 11, 9));


        PublisherEntity publisher = new PublisherEntity();
        publisher.setName("Самиздат");

        BookEntity book = new BookEntity();
        book.setName("О смысле бытия");
        book.setYear(2024);
        book.setPublisher(publisher);
        book.addAuthor(authorFirst);
        book.addAuthor(authorSecond);

        return book;
    }
}
