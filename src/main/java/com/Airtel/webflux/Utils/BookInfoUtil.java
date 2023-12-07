package com.Airtel.webflux.Utils;

import com.Airtel.webflux.DTO.supportDTO.BookInfoDTO;
import com.Airtel.webflux.Entity.Supportclass.BookInfo;
import org.springframework.beans.BeanUtils;

public class BookInfoUtil {
    public static BookInfoDTO entityToDTO(BookInfo bookInfo){
        BookInfoDTO dto=new BookInfoDTO();
        BeanUtils.copyProperties(bookInfo,dto);
        return dto;
    }
    public static BookInfo dtoToEntity (BookInfoDTO dto){
        BookInfo bookInfo=new BookInfo();
        BeanUtils.copyProperties(dto,bookInfo);
        return bookInfo;
    }
}
