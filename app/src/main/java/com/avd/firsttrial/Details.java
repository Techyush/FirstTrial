package com.avd.firsttrial;

public class Details {
    String f_id, name, mail, desc, price, rating;

    public Details(String f_id, String name, String desc, String price, String rating, String mail) {

        this.f_id = f_id;
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.rating = rating;
        this.mail = mail;
    }

    public String getF_id() {
        return f_id;
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


