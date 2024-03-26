package com.othman.onlinebookstore.DTO;

import java.util.List;

import lombok.Data;

@Data
public class TransactionDTO {
    private Integer userId;
    private List<BookCartDTO> books; // [{bookId:1,qunatity:1},{}]
}
