package com.example.shopbangiay.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Categories {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;
    @OneToMany(mappedBy = "categories")
    @Fetch(FetchMode.JOIN)
    private Set<Zay> zays;

    public Categories() {
    }

    public Categories(Long id, String name, Set<Zay> zays) {
        this.id = id;
        this.name = name;
        this.zays = zays;
    }
}
