package org.akira.mybatisjpa.repositories;

import org.akira.mybatisjpa.entities.PublisherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PublisherRepository extends JpaRepository<PublisherEntity, UUID> {
}
