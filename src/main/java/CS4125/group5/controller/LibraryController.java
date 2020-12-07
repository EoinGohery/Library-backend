package CS4125.group5.controller;


import CS4125.group5.dto.BookRequest;
import CS4125.group5.entity.Book;
import CS4125.group5.service.LibraryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/library")
@AllArgsConstructor
public class LibraryController {
    private final LibraryService libraryService;
    @PostMapping("/add")
    public ResponseEntity<String> addBook(@RequestBody BookRequest bookRequest) {
        libraryService.addBook(bookRequest);
        return new ResponseEntity<>("Book has been Registered in the system", HttpStatus.CREATED);
    }
    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<Book>> booksByGenre(@PathVariable String genre) {
        return ResponseEntity.ok(libraryService.getBooksByGenre(genre));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Book> booksById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(libraryService.getBookById(id));
    }
    @GetMapping("/book/{name}")
    public ResponseEntity<Book> booksByName(@PathVariable String name) throws Exception {
        return ResponseEntity.ok(libraryService.getBookByName(name));
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<List<Book>> booksByAuthor(@PathVariable String author) {
        return ResponseEntity.ok(libraryService.getBooksByAuthor(author));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Book>> findAll(){
        return ResponseEntity.ok(libraryService.findAll());
    }



}
