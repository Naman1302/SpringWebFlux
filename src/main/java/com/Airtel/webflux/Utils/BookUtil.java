package com.Airtel.webflux.Utils;

import com.Airtel.webflux.DTO.BookDTO;
import com.Airtel.webflux.Entity.Book;
import org.springframework.beans.BeanUtils;

public class BookUtil {
    public static BookDTO entityToDTO (Book book){
        BookDTO dto=new BookDTO();
        BeanUtils.copyProperties(book,dto);
        return dto;
    }
    public static Book dtoToEntity (BookDTO dto){
        Book book=new Book();
        BeanUtils.copyProperties(dto,book);
        return book;
    }
}
