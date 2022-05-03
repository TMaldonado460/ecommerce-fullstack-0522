package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Cart {

    //Todas las relaciones hechas
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name="id_product",nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name="id_user_info",nullable = false)
    private UserInfo userInfo;


}
