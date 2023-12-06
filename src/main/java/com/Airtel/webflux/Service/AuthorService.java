package com.Airtel.webflux.Service;

import com.Airtel.webflux.DTO.AuthorDTO;
import com.Airtel.webflux.Repository.AuthorRepo;
import com.Airtel.webflux.Utils.AuthorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepo authorRepo;

    public Flux<AuthorDTO> getAllAuthors(){
        return authorRepo.findAll().map(AuthorUtil::entityToDTO);
    }
}
