package org.akira.liquijpa.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.akira.liquijpa.entities.BookEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface BookRepository extends JpaRepository<BookEntity, UUID> {

    @PersistenceContext
    EntityManager entityManager = null;

    @EntityGraph(attributePaths = {"publisher"})
    @Query("select b from BookEntity b left join fetch b.authors aut  where aut is NULL or aut.id = :idAutors")
    List<BookEntity> findByIdTest(UUID idAutors);

    @Query(value = "SELECT b.id as b_id, b.name as b_name, b.year, b.publisher_id, a.id as a_id, a.name as a_name, a.birthday, p.id as p_id, p.name as p_name, " +
            "ba.id as ba_id, ba.book_id, ba.author_id " +
            "FROM book b left join book_author ba on ba.book_id = b.id left join author a on a.id = ba.author_id " +
            "left join publisher p on p.id = b.publisher_id",
    nativeQuery = true)
    List<BookEntity> findAllNative();

    @Query(value = "SELECT b.id as b_id, b.name as b_name, b.year as b_year, p.id as p_id, p.name as p_name " +
            "FROM book b LEFT JOIN publisher p ON p.id = b.publisher_id",
    nativeQuery = true)
    List<Map<String, Object>> findAllNative2();


    @EntityGraph(attributePaths = {"publisher", "authors"})
    @Override
    Optional<BookEntity> findById(UUID uuid);
}
