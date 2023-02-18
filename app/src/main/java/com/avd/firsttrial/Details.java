package com.avd.firsttrial;

public class Details {
    String id;
    String name;
    String surname;
    String mail;

    public Details(String id, String mail, String name, String surname) {
        this.mail = mail;
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public String uid(){
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getMail() {
        return mail;
    }
}


