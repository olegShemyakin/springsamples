package org.akira.liquijpa.contextinitializer;

import org.akira.extension.SpringBootApplicationTest;
import org.akira.liquijpa.entities.BookEntity;
import org.akira.liquijpa.repositories.AuthorRepository;
import org.akira.liquijpa.repositories.BookRepository;
import org.akira.liquijpa.repositories.PublisherRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DirtiesContext
public class DBTest2 extends SpringBootApplicationTest {
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    PublisherRepository publisherRepository;

    @Test
    void testBook() {
        List<BookEntity> books = bookRepository.findAll();

        assertThat(books).isEmpty();
    }
}
