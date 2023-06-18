package com.fpbinar6.code.models;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "airline")
@NoArgsConstructor
@AllArgsConstructor
public class Airline implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false, unique = true)
    private int airlineId;

    @Column(name = "name",nullable = false)
    private String name;

}