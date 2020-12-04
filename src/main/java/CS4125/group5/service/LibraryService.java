package com.example.CS4125.service;

import com.example.CS4125.dto.BookRequest;
import com.example.CS4125.dto.BookResponse;
import com.example.CS4125.dto.RatingDto;
import com.example.CS4125.entity.Book;
import com.example.CS4125.entity.Ratings;
import com.example.CS4125.entity.User;
import com.example.CS4125.repository.BookRepository;
import com.example.CS4125.repository.RatingRepository;
import com.example.CS4125.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class LibraryService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final RatingRepository ratingRepository;
    public void addBook(BookRequest bookRequest){
        Book book = new Book();
        book.setAuthor(bookRequest.getAuthor());
        book.setBookName(bookRequest.getName());
        book.setGenre(bookRequest.getGenre());
        bookRepository.save(book);
    }

    @Transactional(readOnly = true)
    public List<Book> getBooksByGenre(String genre){
        List<Book> books = bookRepository.findAllByGenre(genre);
        return books.stream().collect(toList());
    }
    @Transactional(readOnly = true)
    public Book getBookById(Long id) throws Exception {
        Book book = bookRepository.findById(id).orElseThrow(()-> new Exception());
        return book;
    }

    @Transactional(readOnly = true)
    public Book getBookByName(String name) throws Exception {
        Book book = bookRepository.findByBookName(name).orElseThrow(()-> new Exception());
        return book;
    }

    @Transactional(readOnly = true)
    public List<Book> getBooksByAuthor(String author){
        List<Book> books = bookRepository.findAllByAuthor(author);
        return books.stream().collect(toList());
    }
    @Transactional(readOnly = true)
    public List<Book> findAll(){
        return bookRepository.findAll().stream().collect(toList());
    }
    @Transactional(readOnly = true)
    public List<Ratings> findAllRatings(){
        return ratingRepository.findAll().stream().collect(toList());
    }
    public void addRating(RatingDto ratingDto) throws Exception {
        Book book = bookRepository.findByBookName(ratingDto.getBookName()).orElseThrow(()-> new Exception());
        User user = userRepository.findByUsername(ratingDto.getUserName()).orElseThrow(()-> new Exception());
        Ratings rating = new Ratings();
        rating.setBook(book);
        rating.setRating(ratingDto.getRating());
        rating.setUser(user);
        ratingRepository.save(rating);
    }





}
