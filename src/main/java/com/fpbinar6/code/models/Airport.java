package com.fpbinar6.code.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "airport")
@NoArgsConstructor
@AllArgsConstructor
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private int airportId;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "city_name",nullable = false)
    private String cityName;
}
