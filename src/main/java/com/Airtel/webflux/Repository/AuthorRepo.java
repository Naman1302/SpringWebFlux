package com.Airtel.webflux.Repository;

import com.Airtel.webflux.DTO.AuthorDTO;
import com.Airtel.webflux.Entity.Author;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public interface AuthorRepo extends ReactiveMongoRepository<Author,String> {
    Mono<AuthorDTO> findByName(String name);
}
