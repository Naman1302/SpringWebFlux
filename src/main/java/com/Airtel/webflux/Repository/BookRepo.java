package com.Airtel.webflux.Repository;

import com.Airtel.webflux.DTO.BookDTO;
import com.Airtel.webflux.Entity.Book;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface BookRepo extends ReactiveMongoRepository <Book,String>{
    Flux<BookDTO> findByGenre(String genre);
    //custom queries
    @Query("{'genre' : ?0, 'copiesAvailable' : {$gte : ?1} }")
    Flux<BookDTO> searchByGenreAndCopiesCount(String genre, int copiesAvailable);
}
