package com.example.shopbangiay.model;

import javax.persistence.*;

@Entity
public class ZayImage {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String image;
    @ManyToOne
    @JoinColumn(name = "zay_id")
    private Zay zay;

    public ZayImage() {
    }

    public ZayImage(Long id, String image, Zay zay) {
        this.id = id;
        this.image = image;
        this.zay = zay;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Zay getZay() {
        return zay;
    }

    public void setZay(Zay zay) {
        this.zay = zay;
    }
}
