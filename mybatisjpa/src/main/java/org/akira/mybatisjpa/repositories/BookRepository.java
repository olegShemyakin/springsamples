package org.akira.mybatisjpa.repositories;

import org.akira.mybatisjpa.entities.BookEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BookRepository extends JpaRepository<BookEntity, UUID> {

    @EntityGraph(attributePaths = {"publisher", "authors"})
    @Override
    Optional<BookEntity> findById(UUID uuid);
}
