package com.example.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "home", schema="public")
public class Home extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long homeId;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 100)
    private String subTitle;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false, length = 1)
    private String status;

}
