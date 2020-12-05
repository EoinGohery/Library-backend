package CS4125.group5.service;


import CS4125.group5.dto.BookRequest;
import CS4125.group5.entity.Book;
import CS4125.group5.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class LibraryService {
    private final BookRepository bookRepository;
    public void addBook(BookRequest bookRequest){
        Book book = new Book();
        book.setAuthor(bookRequest.getAuthor());
        book.setBookName(bookRequest.getName());
        book.setGenre(bookRequest.getGenre());
        bookRepository.save(book);
    }

    @Transactional(readOnly = true)
    public Book getBookById(Long id) throws Exception {
        Book book = bookRepository.findById(id).orElseThrow(()-> new Exception());
        return book;
    }


}
