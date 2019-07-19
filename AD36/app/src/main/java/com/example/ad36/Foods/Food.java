package com.example.ad36.Foods;

public class Food {
    String name, descrip, type;
    int id, iconId;
    int price;

    public Food() {
    }

    public Food( int id, String name, String descrip, String type, int iconId, int price) {
        this.name = name;
        this.descrip = descrip;
        this.type = type;
        this.id = id;
        this.iconId = iconId;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
