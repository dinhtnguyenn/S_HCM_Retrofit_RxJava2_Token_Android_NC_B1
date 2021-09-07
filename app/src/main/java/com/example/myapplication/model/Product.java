package com.example.myapplication.model;

public class Product {
    private int no;
    private String productName;
    private String price;

    public Product(int no, String productName, String price) {
        this.no = no;
        this.productName = productName;
        this.price = price;
    }

    public Product(String productName, String price) {
        this.productName = productName;
        this.price = price;
    }


    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
