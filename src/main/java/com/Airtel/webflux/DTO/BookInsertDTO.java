package com.Airtel.webflux.DTO;

public class BookInsertDTO {
    private BookDTO book;
    private String authorName;

    public BookInsertDTO(BookDTO book, String authorName) {
        this.book = book;
        this.authorName = authorName;
    }

    public BookDTO getBook() {
        return book;
    }

    public void setBook(BookDTO book) {
        this.book = book;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
