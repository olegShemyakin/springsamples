package org.akira.liquijpa.repositories;

import org.akira.liquijpa.entities.PublisherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PublisherRepository extends JpaRepository<PublisherEntity, UUID> {
}
