package com.othman.onlinebookstore.entity;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.Check;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class TransactionHistory {
        
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // @ManyToMany
    // @JsonIgnoreProperties("transactions")
    // private List<Book> books;
    
    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<TransactionBook> transactionBooks;

    @ManyToOne
    @JsonManagedReference
    private UserEntity user;

    private Double totalPrice;

    @Temporal(TemporalType.TIMESTAMP)
    private Date issuedData;

    
}