package com.Airtel.webflux.Repository;

import com.Airtel.webflux.Entity.Author;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface AuthorRepo extends ReactiveMongoRepository<Author,String> {

}
