package com.othman.onlinebookstore.entity;

import java.util.List;

import org.hibernate.annotations.Check;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Data
public class Book {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Check(constraints = "price >= 0")
    private Double price;

    @Check(constraints = "quantity >= 0")
    private Integer quantity;

    // @ManyToMany(mappedBy = "books")
    // @JsonIgnore
    // private List<TransactionHistory> transactions;

    @OneToMany(mappedBy = "book" , cascade = CascadeType.ALL)
    @JsonIgnore
    private List<TransactionBook> transactionBooks;

    @Transient
    private boolean available;
    public boolean getAvailable(){
        return quantity > 0;
    }
}
