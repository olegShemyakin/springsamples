package org.akira.mybatisjpa.repositories.mybatis;

import org.akira.mybatisjpa.dto.mybatis.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookRepositoryBatis {

    List<Book> findAll();
}
