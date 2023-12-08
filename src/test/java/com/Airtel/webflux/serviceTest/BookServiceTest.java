package com.Airtel.webflux.serviceTest;

import static org.mockito.Mockito.*;

import com.Airtel.webflux.Controller.BookController;
import com.Airtel.webflux.DTO.BookDTO;
import com.Airtel.webflux.DTO.BookInsertDTO;
import com.Airtel.webflux.Entity.Author;
import com.Airtel.webflux.Entity.Book;
import com.Airtel.webflux.Repository.AuthorRepo;
import com.Airtel.webflux.Repository.BookRepo;
import com.Airtel.webflux.Service.BookService;
import com.Airtel.webflux.Utils.BookUtil;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

@WebFluxTest(BookController.class)
public class BookServiceTest {
    @Autowired
    private WebTestClient webTestClient;
    @MockBean
    private BookService bookService;
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private AuthorRepo authorRepo;
    public final ObjectId bookId1=new ObjectId(),bookId2=new ObjectId();
    public final ObjectId authorId1=new ObjectId(),authorId2=new ObjectId();
    public final BookDTO testBook=new BookDTO(bookId1, "Haiku", "Science Fiction", 1, authorId1);
    public final Book tryBook=new Book(bookId1, "Haiku", "Science Fiction", 2, authorId1);

    @Test
    public void addBookTest(){
        BookInsertDTO request=new BookInsertDTO(testBook,"Ram");
        Mono<BookDTO> tempBook= Mono.just(new BookDTO(bookId1, "Haiku", "Science Fiction", 1, authorId1));
        when(bookService.addBook(request)).thenReturn(tempBook);

        webTestClient.post().uri("/books")
                .body(Mono.just(tempBook),BookDTO.class)
                .exchange()
                .expectStatus().isOk();
    }
    @Test
    public void getAllBooksTest(){
        Flux<BookDTO> bookDTOFlux=Flux.just(testBook);
        when(bookService.getAllBooks()).thenReturn(bookDTOFlux);

        webTestClient.get().uri("/books")
                .exchange()
                .expectStatus().isOk()
                .returnResult(BookDTO.class)
                .getResponseBody();
    }
    @Test
    public void getBooksByGenreTest(){
        String genre="Science Fiction";
        Flux<BookDTO> bookDTOFlux=Flux.just(testBook);

        when(bookService.getBookByGenre(genre)).thenReturn(bookDTOFlux);

        webTestClient.get().uri(uriBuilder ->
                uriBuilder
                        .path("/books/byGenre")
                        .queryParam("genre", genre)
                        .build())
                .exchange()
                .expectStatus().isOk()
                .returnResult(BookDTO.class)
                .getResponseBody();
    }
    @Test
    void getBooksByGenreAndCopiesAvailable() {
        Flux<Book> books = Flux.just(tryBook);

        bookRepo.saveAll(books).blockLast();

        Flux<BookDTO> bookDTOFlux = bookService.getBooksByGenreAndCopies("Science Fiction",1);

        StepVerifier.create(bookDTOFlux)
                .expectNextCount(1)
                .verifyComplete();
    }
    @Test
    void getBooksByAuthorsName() {
        Author author1 = new Author();
        author1.setName("a1");
        author1.setId(authorId1);
        Author author2 = new Author();
        author2.setName("a2");
        author2.setId(authorId2);

        authorRepo.saveAll(Flux.just(author1, author2)).blockLast();

        Flux<Book> books = Flux.just(
                new Book(bookId1,"Jolly", "Science Fiction", 1, authorId1),
                new Book(bookId2,"Jurrasic", "Fantasy", 2, authorId1)
        );

        bookRepo.saveAll(books).blockLast();

        Flux<BookDTO> bookDTOFlux = bookService.getBooksByAuthorsName("a1:a2");

        StepVerifier.create(bookDTOFlux)
                .expectNextCount(2)
                .verifyComplete();
    }
}
