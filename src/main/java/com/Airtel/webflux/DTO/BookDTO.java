package com.Airtel.webflux.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

public class BookDTO {
    @Id
    private ObjectId id;
    @Indexed(unique = true)
    @NotBlank(message = "Name is mandatory")
    private String bookName;
    @NotBlank(message = "Genre is mandatory")
    private String genre;
    @Min(value=1)
    private int copiesAvailable;
    private ObjectId authorId;

    public BookDTO() {
    }

    public BookDTO(ObjectId id, String bookName, String genre, int copiesAvailable, ObjectId authorId) {
        this.id = id;
        this.bookName = bookName;
        this.genre = genre;
        this.copiesAvailable = copiesAvailable;
        this.authorId = authorId;
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

    public ObjectId getAuthorId() {
        return authorId;
    }

    public void setAuthorId(ObjectId authorId) {
        this.authorId = authorId;
    }
}
