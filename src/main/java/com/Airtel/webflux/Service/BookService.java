package com.Airtel.webflux.Service;


import com.Airtel.webflux.DTO.AuthorDTO;
import com.Airtel.webflux.DTO.BookDTO;
import com.Airtel.webflux.DTO.BookInsertDTO;
import com.Airtel.webflux.DTO.supportDTO.BookInfoDTO;
import com.Airtel.webflux.Entity.Supportclass.BookInfo;
import com.Airtel.webflux.Entity.Book;
import com.Airtel.webflux.Repository.AuthorRepo;
import com.Airtel.webflux.Repository.BookRepo;
import com.Airtel.webflux.Utils.AuthorUtil;
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
                        BookDTO saveBook = BookUtil.entityToDTO(requestMono.getBook());
                        saveBook.setAuthorId(author.getId());
                        return Mono.just(saveBook);
                    }
                    else{
                        return Mono.error(new NullPointerException("Author Not found"));
                    }
                });
        Mono<BookDTO> response=bookDTOMono.map(BookUtil::dtoToEntity)
                .flatMap(bookRepo::insert)
                .map(BookUtil::entityToDTO);
//        AuthorDTO foundAuthor=authorRepo.findByName(authorName)
//                .flatMap((author)->
//                        List<BookInfo> test=author.getBookList();
//                        return null;
//                )
        return response;
    }

    public Flux<BookDTO> getBooksByGenreAndCopies(String genre, int id) {
        return bookRepo.searchByGenreAndCopiesCount(genre,id);
    }
//
//    public Flux<BookDTO> getBooksByAuthorsName(String authorList) {
//        String[] authors= authorList.split(":");
//        return Flux.fromArray(authors)
//                .flatMap(authorName -> authorRepo.findByName(authorName)
//                        .flatMapMany(this::getBooks));
//    }
//    private Flux<BookDTO> getBooks(AuthorDTO authorDTO){
//        if(authorDTO!=null && authorDTO.getBookList()!=null){
//            List<BookInfoDTO> bookInfoDTOList = authorDTO.getBookList();
//            return Flux.fromIterable(bookInfoDTOList)
//                    .flatMap(bookInfoDTO -> bookRepo.findById(bookInfoDTO.getId())
//                            .map(this::convertToDTO)
//                    );
//        }
//        else{
//            return Flux.empty();
//        }
//    }
}
