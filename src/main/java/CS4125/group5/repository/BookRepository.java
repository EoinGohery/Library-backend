package com.example.CS4125.repository;

import com.example.CS4125.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    Optional<Book> findById(Long aLong);
    Optional<Book> findByBookName(String name);
    List<Book> findAllByAuthor(String author);
    List<Book> findAllByGenre(String genre);
}
