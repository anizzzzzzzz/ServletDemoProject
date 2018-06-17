package com.onlineproduct.entity;

public class Product {
    private String pCode;
    private String name;
    private float price;

    public Product(){}

    public Product(String pCode,String name,float price){
        this.pCode=pCode;
        this.name=name;
        this.price=price;
    }

    public String getpCode() {
        return pCode;
    }

    public void setpCode(String pCode) {
        this.pCode = pCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
