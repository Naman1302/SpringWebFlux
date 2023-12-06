package com.Airtel.webflux.Controller;

import com.Airtel.webflux.DTO.BookDTO;
import com.Airtel.webflux.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
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
    public Flux<BookDTO> getBooksByGenreAndCopies(@RequestParam String genre,@RequestParam int id){ return bookService.getBooksByGenreAndCopies(genre,id);}
    @PostMapping
    public Mono<BookDTO> addBook(@RequestBody Mono<BookDTO> bookDTO){
        return bookService.addBook(bookDTO);
    }
}
