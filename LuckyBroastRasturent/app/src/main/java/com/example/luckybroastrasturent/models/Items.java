package com.example.luckybroastrasturent.models;

public class Items {
    String name, desc,image, category;
    int price;
    boolean pin;


    public Items(String name, String desc, String image, String category, int price, boolean pin) {
        this.name = name;
        this.desc = desc;
        this.image = image;
        this.category = category;
        this.price = price;
        this.pin = pin;
    }

    public Items() {
    }

    public boolean isPin() {
        return pin;
    }

    public void setPin(boolean pin) {
        this.pin = pin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
