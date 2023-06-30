package com.fpbinar6.code.models;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "airport")
@NoArgsConstructor
@AllArgsConstructor
public class Airport implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false, unique = true)
    private int airportId;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "code", nullable = false)
    private String code;
    
    @Column(name = "city_name",nullable = false)
    private String cityName;

    @Column(name = "city_image",nullable = false)
    private String cityImage;
}
