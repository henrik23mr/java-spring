package com.henrik23mr.crud_bookstore_demo1.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Book")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private String isbn;

}
