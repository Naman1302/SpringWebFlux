package com.Airtel.webflux.Service;


import com.Airtel.webflux.DTO.AuthorDTO;
import com.Airtel.webflux.DTO.BookDTO;
import com.Airtel.webflux.DTO.BookInsertDTO;
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

    public Mono<BookDTO> addBook(BookInsertDTO requestMono){
        String authorName=requestMono.getAuthorName();
        Mono<BookDTO> bookDTOMono=authorRepo.findByName(authorName)
                .flatMap((author) -> {
                    if(author!=null) {
                        BookDTO saveBook = requestMono.getBookDTO();
                        saveBook.setAuthorId(author.getId());
                        return Mono.just(saveBook);
                    }
                    else{
                        return Mono.error(new IllegalArgumentException("Author Not found"));
                    }
                });
        Mono<BookDTO> response=bookDTOMono.map(BookUtil::dtoToEntity)
                .flatMap(bookRepo::insert)
                .map(BookUtil::entityToDTO);
        System.out.println("Book saved");
//        Mono<AuthorDTO> updatedAuthorMono = bookDTOMono
//                .map(BookUtil::dtoToEntity)
//                .flatMap(savedBook -> authorRepo.findByName(authorName)
//                        .flatMap(author -> {
//                            if (author != null) {
//                                BookInfo newBookInfo = new BookInfo();
//                                newBookInfo.setId(savedBook.getId());
//                                newBookInfo.setBookName(savedBook.getBookName());
//
//                                author.getBookList().add(newBookInfo);
//                                System.out.println("Added book to list" );
//                                return authorRepo.save(AuthorUtil.dtoToEntity(author)).map(AuthorUtil::entityToDTO);
//                            } else {
//                                return Mono.error(new NullPointerException("Author Not found"));
//                            }
//                        }));
        return response;
    }

    public Flux<BookDTO> getBooksByGenreAndCopies(String genre, int copies) {
        return bookRepo.searchByGenreAndCopiesCount(genre,copies);
    }

    public Flux<BookDTO> getBooksByAuthorsName(String authorList) {
        String[] authors= authorList.split(":");
        return Flux.fromArray(authors)
                .flatMap(authorName -> authorRepo.findByName(authorName)
                        .flatMapMany(this::getBooks));
    }
    private Flux<BookDTO> getBooks(AuthorDTO authorDTO){
        if(authorDTO!=null && authorDTO.getId()!=null){
            return bookRepo.findBookWhereAuthorIsIn(authorDTO.getId());
        }
        else{
            return Flux.empty();
        }
    }
}
