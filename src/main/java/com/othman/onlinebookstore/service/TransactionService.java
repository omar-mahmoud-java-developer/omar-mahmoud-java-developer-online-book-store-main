package com.othman.onlinebookstore.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.othman.onlinebookstore.DTO.BookCartDTO;
import com.othman.onlinebookstore.DTO.TransactionDTO;
import com.othman.onlinebookstore.entity.Book;
import com.othman.onlinebookstore.entity.TransactionBook;
import com.othman.onlinebookstore.entity.TransactionHistory;
import com.othman.onlinebookstore.entity.UserEntity;
import com.othman.onlinebookstore.repository.TransactionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionService {
    
    private final TransactionRepository transactionRepository;
    private final UserService userService;
    private final BookService bookService;

    // get all transcations
    public List<TransactionHistory> getAllTransactions(){
        return transactionRepository.findAll();
    }
    // get transaction by id
    public TransactionHistory getTransactionById(Integer id){
        return transactionRepository.findById(id).orElse(null);
    }
    // get transaction by userId
    public List<TransactionHistory> getTransactionByUserId(Integer id){
      return transactionRepository.findByUserId(id);
  }
    // delete transction by id
    public void deleteTransactionById(Integer id){
        transactionRepository.deleteById(id);
    }

    // create transaction
    @Transactional
    public  TransactionHistory createTransaction(TransactionDTO transactionDTO){
        UserEntity user = userService.getUserById(transactionDTO.getUserId());
        List<TransactionBook> transactionBooksList = new ArrayList<>();
        TransactionHistory transaction = new TransactionHistory();     //  [bookObj,bookObj]
        List<BookCartDTO> requiredBooks = transactionDTO.getBooks(); // [{bookId:1,quantity:1},{bookId:2,quantity:1}]
        List<Integer> bookIds = requiredBooks.stream().map(bookObj -> bookObj.getBookId()).toList(); // [1,2]
        List<Book> books = bookService.getAllBooksByIds(bookIds); // books [{id:1,name:c++,price:20,quantity:10},{id:1,name:c++,price:20,quantity:10}]
        Double totalPrice = 0.0;
        // update quantity in db
        for(Book book : books){
          for(BookCartDTO bookObj : requiredBooks){
            if (book.getId() == bookObj.getBookId()) {
               // update quantity in db
                book.setQuantity(book.getQuantity() - bookObj.getQuantity());
                // calculate total price
                totalPrice += book.getPrice() * bookObj.getQuantity();
                // save transaction book
                TransactionBook tb = new TransactionBook();
                tb.setBook(book);
                tb.setQuantity(bookObj.getQuantity());
                tb.setName(book.getName());
                tb.setPrice(book.getPrice());
                tb.setTransaction(transaction);
                transactionBooksList.add(tb);
            }
          }
        }

        // set transaction
          transaction.setTransactionBooks(transactionBooksList);
          transaction.setTotalPrice(totalPrice);
          transaction.setUser(user);
          transaction.setIssuedData(new Date());
        // save Transcation
        return transactionRepository.save(transaction);
    }

}
