package com.Airtel.webflux.DTO;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class BookDTO {
    @Id
    private ObjectId id;
    private String bookName;
    private String genre;
    private int copiesAvailable;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getCopiesAvailable() {
        return copiesAvailable;
    }

    public void setCopiesAvailable(int copiesAvailable) {
        this.copiesAvailable = copiesAvailable;
    }
}
