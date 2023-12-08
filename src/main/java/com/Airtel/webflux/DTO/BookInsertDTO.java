package com.Airtel.webflux.DTO;

import com.Airtel.webflux.Entity.Book;

public class BookInsertDTO {
    private BookDTO bookDTO;
    private String authorName;

    public BookInsertDTO(BookDTO bookDTO, String authorName) {
        this.bookDTO = bookDTO;
        this.authorName = authorName;
    }

    public BookDTO getBookDTO() {
        return bookDTO;
    }

    public void setBookDTO(BookDTO bookDTO) {
        this.bookDTO = bookDTO;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
