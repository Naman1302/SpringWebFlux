package com.Airtel.webflux.Controller;

import com.Airtel.webflux.DTO.BookDTO;
import com.Airtel.webflux.DTO.BookInsertDTO;
import com.Airtel.webflux.Service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;
    @GetMapping
    public Flux<BookDTO> getAllBooks(){
        return bookService.getAllBooks();
    }
    @GetMapping("/getByGenre")
    public Flux<BookDTO> getBooksByGenre(@RequestParam String genre){
        return bookService.getBookByGenre(genre);
    }
    @GetMapping("/getByGenreAndCopiesCount")
    public Flux<BookDTO> getBooksByGenreAndCopies(@RequestParam String genre,@RequestParam int copies){ return bookService.getBooksByGenreAndCopies(genre,copies);}
    @GetMapping("/byAuthorsNames")
    public Flux<BookDTO> getBooksByAuthorsNames(@RequestParam String authorList){ return bookService.getBooksByAuthorsName(authorList);}
    @GetMapping("/byBookIds")
    public  Flux<BookDTO> getBooksByIds(@RequestParam String bookIdList){ return bookService.getBooksByIds(bookIdList);}
    @PostMapping
    public ResponseEntity<Mono<BookDTO>> addBook(@Valid @RequestBody  BookInsertDTO request){
        return ResponseEntity.ok(bookService.addBook(request));
    }
}
