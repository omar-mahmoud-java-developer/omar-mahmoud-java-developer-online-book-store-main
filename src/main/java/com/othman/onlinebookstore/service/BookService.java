package com.othman.onlinebookstore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.othman.onlinebookstore.DTO.BookDTO;
import com.othman.onlinebookstore.entity.Book;

import com.othman.onlinebookstore.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {
    
    private final BookRepository bookRepository;

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    
    public List<Book> getAllBooksByIds(List<Integer> bookIds ){
        return bookRepository.findAllByIdIn(bookIds);
    }
    
    public Book getBookById(Integer id){
        return bookRepository.findById(id).orElse(null);
    }
    public Book createBook(BookDTO bookDTO){
        Book newBook = new Book();
        newBook.setName(bookDTO.getName());
        newBook.setPrice(bookDTO.getPrice());
        newBook.setQuantity(bookDTO.getQuantity());
        return bookRepository.save(newBook);
    }
    
    public Book replaceBook (Integer id,BookDTO bookDTO){
        Book newBook = new Book();
        newBook.setId(id);
        newBook.setName(bookDTO.getName());
        newBook.setPrice(bookDTO.getPrice());
        newBook.setQuantity(bookDTO.getQuantity());
        return bookRepository.save(newBook);
    }

    public Book updateBook(Integer id,BookDTO bookDTO){
        Book oldBook = bookRepository.findById(id).orElse(null);
        if (bookDTO.getName() != null) {
            oldBook.setName(bookDTO.getName());
        }
        if (bookDTO.getPrice() != null) {
            oldBook.setPrice(bookDTO.getPrice());
        }
        if (bookDTO.getQuantity() != null) {
            oldBook.setQuantity(bookDTO.getQuantity());
        }

        return bookRepository.save(oldBook);
    }

    public void deleteBookById(Integer id){
        bookRepository.deleteById(id);
    }
}
