package com.Airtel.webflux.Service;


import com.Airtel.webflux.DTO.AuthorDTO;
import com.Airtel.webflux.DTO.BookDTO;
import com.Airtel.webflux.DTO.supportDTO.BookInfoDTO;
import com.Airtel.webflux.Entity.Book;
import com.Airtel.webflux.Repository.AuthorRepo;
import com.Airtel.webflux.Repository.BookRepo;
import com.Airtel.webflux.Utils.BookUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookService {
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private AuthorRepo authorRepo;
    public Flux<BookDTO> getAllBooks(){
        return bookRepo.findAll().map(BookUtil::entityToDTO);
    }
    public Flux<BookDTO> getBookByGenre(String genre){
        return bookRepo.findByGenre(genre);
    }
//    public Mono<BookDTO> addBook(BookInsertDTO bookInsertDTO){
//        String authorName=bookInsertDTO.getAuthorName();
//        AuthorDTO foundAuthor=authorRepo.findByName(authorName);
//        if(foundAuthor==null){
//            AuthorDTO newAuthor=new AuthorDTO();
//            newAuthor.setName(authorName);
//            authorRepo.save(newAuthor);
//            book.setAuthorId(newAuthor.getid());
//        }
//        else{
//            book.setAuthorId(foundAuthor.getid());
//        }
//        Book savedBook=bookRepo.save(book);
//        foundAuthor.getBookList().add(savedBook.getId());
//        authorRepo.save(foundAuthor);
//        return savedBook;
//    }

    public Mono<BookDTO> addBook(Mono<BookDTO> bookDTOMono){
        return bookDTOMono.map(BookUtil::dtoToEntity)
                .flatMap(bookRepo::insert)
                .map(BookUtil::entityToDTO);
    }

    public Flux<BookDTO> getBooksByGenreAndCopies(String genre, int id) {
        return bookRepo.searchByGenreAndCopiesCount(genre,id);
    }

//    public Flux<BookDTO> getBooksByAuthorsName(String authorList) {
//        String[] authors= authorList.split(":");
//        return Flux.fromArray(authors)
//                .flatMap(authorName -> authorRepo.findByName(authorName)
//                        .flatMapMany(this::getBooks));
//    }
//    private Flux<BookDTO> getBooks(AuthorDTO authorDTO){
//
//    }
}
