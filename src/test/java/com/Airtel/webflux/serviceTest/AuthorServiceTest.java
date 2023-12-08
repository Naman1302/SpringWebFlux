package com.Airtel.webflux.serviceTest;

import com.Airtel.webflux.DTO.AuthorDTO;
import com.Airtel.webflux.DTO.BookDTO;
import com.Airtel.webflux.Entity.Author;
import com.Airtel.webflux.Entity.Book;
import com.Airtel.webflux.Repository.AuthorRepo;
import com.Airtel.webflux.Service.AuthorService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


@SpringBootTest
class AuthorServiceTest {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private AuthorRepo authorRepository;

    public final ObjectId bookId1=new ObjectId(),bookId2=new ObjectId();
    public final ObjectId authorId1=new ObjectId(),authorId2=new ObjectId();
    public final BookDTO testBook=new BookDTO(bookId1, "Haiku", "Science Fiction", 1, authorId1);
    public final Book tryBook=new Book(bookId1, "Haiku", "Science Fiction", 2, authorId1);


    @Test
    void getAllAuthors() {
        Flux<Author> authors = Flux.just(
                new Author(authorId1, "Author1", null),
                new Author(authorId2, "Author2", null)
        );

        authorRepository.saveAll(authors).blockLast();

        Flux<AuthorDTO> authorDTOFlux = authorService.getAllAuthors();

        StepVerifier.create(authorDTOFlux)
                .expectNextCount(2)
                .verifyComplete();
    }

    @Test
    void saveAuthor() {
        Mono<AuthorDTO> authorDTO = Mono.just(new AuthorDTO(authorId1, "New Author", null));

        StepVerifier.create(authorService.addAuthor(authorDTO))
                .expectNextMatches(savedAuthorDTO ->
                        savedAuthorDTO.getId().equals(authorId1) && savedAuthorDTO.getName().equals("New Author"))
                .verifyComplete();
    }

    @Test
    void getAuthorsByNameRegex() {
        Flux<Author> authors = Flux.just(
                new Author(authorId2, "Author1", null ),
                new Author(authorId1, "Author2", null)
        );

        authorRepository.saveAll(authors).blockLast();

        Flux<AuthorDTO> authorDTOFlux = authorService.getAllAuthorsByNamesLike("Author");

        StepVerifier.create(authorDTOFlux)
                .expectNextCount(2)
                .verifyComplete();
    }
}
