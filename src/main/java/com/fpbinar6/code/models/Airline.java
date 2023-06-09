package com.fpbinar6.code.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "airline")
public class Airline {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private int airlineId;

    @Column(name = "name",nullable = false)
    private String name;

}