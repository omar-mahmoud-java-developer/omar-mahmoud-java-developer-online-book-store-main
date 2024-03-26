package com.othman.onlinebookstore.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.othman.onlinebookstore.DTO.TransactionDTO;
import com.othman.onlinebookstore.entity.TransactionHistory;
import com.othman.onlinebookstore.service.TransactionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/transactions")
public class TransactionController {
    
    private final TransactionService transactionService;

    @GetMapping
    public ResponseEntity<List<TransactionHistory>> getAll(){
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionHistory> getOne(@PathVariable Integer id){
        return ResponseEntity.ok(transactionService.getTransactionById(id));
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<List<TransactionHistory>> getUser(@PathVariable Integer id){
        return ResponseEntity.ok(transactionService.getTransactionByUserId(id));
    }
    
    @PostMapping
    public ResponseEntity<TransactionHistory> createOne(@RequestBody TransactionDTO transactionDTO ){
        return ResponseEntity.ok(transactionService.createTransaction(transactionDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOne(@PathVariable Integer id){
        transactionService.deleteTransactionById(id);
        return ResponseEntity.noContent().build();
    }

}
