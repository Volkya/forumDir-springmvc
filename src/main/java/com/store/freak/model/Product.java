package com.store.freak.model;

public class Product {

    private int id;
    private String name;
    private String description;
    private String urlpic;
    private Integer price;

    public Product(int i, String n, String d, String u, int p) {
    this.name = n;
    this.description = d;
    this.urlpic = u;
    this.price = p;
    this.id = i;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlpic() {
        return urlpic;
    }

    public void setUrlpic(String urlpic) {
        this.urlpic = urlpic;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}

