package CS4125.group5.service;


import CS4125.group5.dto.BookRequest;
import CS4125.group5.dto.RatingDto;
import CS4125.group5.entity.AddedBooks;
import CS4125.group5.entity.Book;
import CS4125.group5.entity.Ratings;
import CS4125.group5.entity.User;
import CS4125.group5.repository.AddedBooksRepository;
import CS4125.group5.repository.BookRepository;
import CS4125.group5.repository.RatingRepository;
import CS4125.group5.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class LibraryService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final RatingRepository ratingRepository;
    private final AddedBooksRepository addedBooksRepository;
    public void addBook(BookRequest bookRequest) throws Exception {
        Book book = new Book();
        AddedBooks addedBook = new AddedBooks();
        book.setAuthor(bookRequest.getAuthor());
        book.setBookName(bookRequest.getName());
        book.setGenre(bookRequest.getGenre());
        User user = userRepository.findByUsername(bookRequest.getUsername()).orElseThrow(()-> new Exception());
        addedBook.setUser(user);
        addedBook.setBook(book);
        bookRepository.save(book);
        addedBooksRepository.save(addedBook);
    }


    @Transactional(readOnly = true)
    public Book getBookById(Long id) throws Exception {
        Book book = bookRepository.findById(id).orElseThrow(()-> new Exception());
        return book;
    }

    @Transactional(readOnly = true)
    public List<Book> getBooksByGenre(String genre){
        List<Book> books = bookRepository.findAllByGenre(genre);
        return books.stream().collect(toList());
    }
    @Transactional(readOnly = true)
    public List<Book> getBookByName(String name) throws Exception {
        List<Book> books = bookRepository.findAllByBookName(name);
        return books.stream().collect(toList());
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

    public boolean containsSwearWords(String bookname) throws Exception {
        if(bookname.contains("Shize")){
            throw new Exception("Book name contains bad language");
        }
        return false;
    }

}
