package com.example.kokwei217.unmcrs;

import java.util.ArrayList;

public class CartComponent {
    public String name;
    public int quantity;

//    public CartComponent(String name, int quantity) {
//        this.name = name;
//        this.quantity = quantity;

    public CartComponent(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }
    public String getName() {
        return name;
    }

    public int getQuantity(){
        return quantity;
    }


}