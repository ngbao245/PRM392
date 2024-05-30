package com.example.lab4_1.Entities;

import java.io.Serializable;

public class Food implements Serializable {
    private String Name;
    private int Money;
    private int Image;

    public Food() {
    }

    public Food(String name, int money, int image) {
        Name = name;
        Money = money;
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getMoney() {
        return Money;
    }

    public void setMoney(int money) {
        Money = money;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }
}
