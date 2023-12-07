package com.Airtel.webflux.DTO;

import com.Airtel.webflux.Entity.Supportclass.Address;
import com.Airtel.webflux.Entity.Supportclass.BookInfo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

public class AuthorDTO {
    @Id
    private ObjectId id;
    @NotBlank(message = "Name is Mandatory")
    private String name;
    @NotNull(message = "Address should be present")
    private Address address;
    private List<BookInfo> bookList;

    public AuthorDTO() {
        this.bookList=new ArrayList<>();
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<BookInfo> getBookList() {
        return bookList;
    }

    public void setBookList(List<BookInfo> bookList) {
        this.bookList = bookList;
    }
}
