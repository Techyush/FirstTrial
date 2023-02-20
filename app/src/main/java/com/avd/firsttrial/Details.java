package com.avd.firsttrial;

public class Details {
    String id, name, mail, desc, price, rating;

    public Details(String id, String mail, String name, String desc, String price, String rating) {
        this.mail = mail;
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.rating = rating;
    }

    public String uid(){
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getMail() {
        return mail;
    }

    public String getPrice() {
        return price;
    }

    public String getRating() {
        return rating;
    }
}


