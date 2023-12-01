package com.Airtel.webflux.Repository;

import com.Airtel.webflux.Entity.Book;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface BookRepo extends ReactiveMongoRepository <Book,String>{
    //custom queries
}
