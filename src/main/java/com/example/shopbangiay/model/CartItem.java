package com.example.shopbangiay.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class CartItem {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private Double price;
    private Double quantity;
    private Double total;
    private String avatar;
    private Date date;
    @OneToOne
    private Users users;
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    public CartItem() {
    }

    public CartItem(Long id, Double price, Double quantity, Double total, String avatar, Date date) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
        this.avatar = avatar;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
