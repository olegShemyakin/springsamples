package org.akira.liquijpa.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.Metamodel;
import lombok.RequiredArgsConstructor;
import org.akira.liquijpa.entities.AuthorEntity;
import org.akira.liquijpa.entities.BookEntity;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class TestRep {

    @PersistenceContext
    private final EntityManager em;

    public List<BookEntity> getBooks() {
        Metamodel m = em.getMetamodel();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<BookEntity> root = cq.from(BookEntity.class);

        EntityType<BookEntity> BookEntity_ = m.entity(BookEntity.class);
        EntityType<AuthorEntity> AuthorEntity_ = m.entity(AuthorEntity.class);

        //Join<BookEntity, AuthorEntity> author = root.join(BookEntity_.getSet("authors", AuthorEntity.class));
        Join<BookEntity, AuthorEntity> author = (Join<BookEntity, AuthorEntity>) root.fetch(BookEntity_.getSet("authors", AuthorEntity.class), JoinType.RIGHT);

        cq.multiselect(root, author);

        ParameterExpression<UUID> idAuthor = cb.parameter(UUID.class);
        cq.where(cb.equal(author.get("id"), idAuthor));

        TypedQuery<Tuple> q = em.createQuery(cq);
        //q.setParameter(idAuthor, UUID.fromString("0a4e2e33-3db5-46b9-829b-36025126066b"));
        q.setParameter(idAuthor, UUID.fromString("f56002e5-1f95-4fff-bf93-ccad8fef9b3f"));
        List<Tuple> result = q.getResultList();

        return List.of();
    }

    public ResultSet ab() {
        return null;
    }
}
