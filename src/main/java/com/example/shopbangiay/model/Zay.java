package com.example.shopbangiay.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Zay {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;
    private Long size;
    private String manufacturer;
    @ManyToOne
    @JoinColumn(name = "zay_id")
    private Categories categories;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;
    @OneToMany(mappedBy = "zay")
    private List<ZayImage> images;


    public Zay() {
    }

    public Zay(Long id, String name, Long size, String manufacturer, Categories categories, Users users) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.manufacturer = manufacturer;
        this.categories = categories;
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public Users getUser() {
        return users;
    }

    public void setUser(Users users) {
        this.users = users;
    }
}
