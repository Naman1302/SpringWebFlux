package com.Airtel.webflux.Entity.Supportclass;

import org.bson.types.ObjectId;

public class BookInfo {
    private ObjectId id;
    private String bookName;

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
}
