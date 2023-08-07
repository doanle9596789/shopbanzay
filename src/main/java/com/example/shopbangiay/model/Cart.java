package com.example.shopbangiay.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Cart {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItems;

    public Cart() {
    }

    public Cart(Long id, List<CartItem> cartItems) {
        this.id = id;
        this.cartItems = cartItems;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
}
