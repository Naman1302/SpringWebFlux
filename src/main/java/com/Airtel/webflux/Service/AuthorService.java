package com.Airtel.webflux.Service;

import com.Airtel.webflux.DTO.AuthorDTO;
import com.Airtel.webflux.Repository.AuthorRepo;
import com.Airtel.webflux.Utils.AuthorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepo authorRepo;

    public Flux<AuthorDTO> getAllAuthors(){
        return authorRepo.findAll().map(AuthorUtil::entityToDTO);
    }
    public Mono<AuthorDTO> addAuthor(Mono<AuthorDTO> authorDTOMono){
        System.out.println("Author Saved!!");
        return authorDTOMono.map(AuthorUtil::dtoToEntity)
                .flatMap(authorRepo::insert)
                .map(AuthorUtil::entityToDTO);
    }
    @Query("{name:{$regex:?0, $options :'i'}}")
    public Flux<AuthorDTO> getAllAuthorsByNamesLike(String authorPattern) {
        return authorRepo.searchAuthorsByNamesLike(authorPattern);
    }
}
