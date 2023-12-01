package com.Airtel.webflux.Entity;

import com.Airtel.webflux.Entity.Supportclass.Address;
import com.Airtel.webflux.Entity.Supportclass.BookInfo;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "author")
public class Author {
    @Id
    private ObjectId id;
    private String name;
    private Address address;
    private List<BookInfo> bookList;

    public Author(ObjectId id, String name, Address address) {
        this.id = id;
        this.name = name;
        this.address = address;
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
