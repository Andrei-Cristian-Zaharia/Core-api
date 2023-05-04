package com.licenta.core.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "restaurant")
public class Restaurant {

    @Id
    @Column(name = "id_restaurant")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "employ_announce")
    private String employAnnounce;

    @Column(name = "page_text")
    private String pageText;

    @Column(name = "front_image")
    private String frontImage;

    @Column(name = "banner_image")
    private String bannerImage;

    @Column(name = "telephone")
    private Integer telephone;

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "website_address")
    private String websiteAddress;

    @Column(name = "address_location")
    private String addressLocation;

    @Column(name = "status")
    private String status;

    @Column(name = "schedule")
    private String schedule;

    @Column(name = "instagram_link")
    private String instagramLink;

    @Column(name = "facebook_link")
    private String facebookLink;

    @Column(name = "partner_since")
    private LocalDate partnerSince;

    @OneToOne
    @JoinColumn(name = "id_person")
    private Person owner;
}
