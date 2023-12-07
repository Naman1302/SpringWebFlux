package com.Airtel.webflux.Controller;

import com.Airtel.webflux.DTO.AuthorDTO;
import com.Airtel.webflux.Service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    public AuthorService authorService;

    @GetMapping
    public Flux<AuthorDTO> getAllAuthors(){
        return authorService.getAllAuthors();
    }
    @GetMapping("/byNamesLike")
    public Flux<AuthorDTO> getAllByNamesLike(@RequestParam String authorPattern) {
        return authorService.getAllAuthorsByNamesLike(authorPattern);
    }

//    @PostMapping
//    public Mono<ServerResponse> addAuthor(@RequestBody @Valid AuthorDTO authorDTO){
//        return authorService.addAuthor(Mono.just(authorDTO))
//                .flatMap(author -> ServerResponse.ok().bodyValue(Mono.just(author)))
//                .onErrorResume(error -> {
//                    System.err.println("Error occurred while adding author: " + error.getMessage());
//                    return Mono.error(error);
//                });
//    }
    @PostMapping
    public ResponseEntity<Mono<AuthorDTO>> addAuthor(@RequestBody @Valid AuthorDTO authorDTO){
        return ResponseEntity.ok(authorService.addAuthor(Mono.just(authorDTO)));
    }
}
