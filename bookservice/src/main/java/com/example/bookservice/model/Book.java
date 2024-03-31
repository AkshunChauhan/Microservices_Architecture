package com.example.bookservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="title")
    private String title;
    @Column(name="author")
    private String author;
}
