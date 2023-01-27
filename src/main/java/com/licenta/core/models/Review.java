package com.licenta.core.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "review")
public class Review {

    @Id
    @Column(name = "id_review")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "text")
    private String text;

    @Column(name = "creationdate")
    private LocalDate creationDate;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "category")
    private String category;

    @ManyToOne
    @JoinColumn(name = "id_person")
    private Person ownerId;

    @ManyToOne
    @JoinColumn(name = "id_recipe")
    private Recipe recipeId;

    @ManyToOne
    @JoinColumn(name = "id_restaurant")
    private Restaurant restaurantId;
}
