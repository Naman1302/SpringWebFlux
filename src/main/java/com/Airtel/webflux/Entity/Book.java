package com.Airtel.webflux.Entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "book")
public class Book {

    @Id
    private ObjectId id;
    @NotBlank(message = "Name is mandatory")
    private String bookName;
    @NotBlank(message = "Genre is mandatory")
    private String genre;
    @NotNull(message = "Copies available is mandatory")
    private int copiesAvailable;

    public Book() {
    }

    public Book(ObjectId id, String bookName, String genre, int copiesAvaliable) {
        this.id = id;
        this.bookName = bookName;
        this.genre = genre;
        this.copiesAvailable = copiesAvaliable;
    }

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
