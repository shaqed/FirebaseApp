package com.example.shaked.firebaseapp;

public class Fruit {

    // To make this class available for upload to Firebase database
    // make sure that these fields are the same as they are in Firebase
    private String nameOfFruit;
    private boolean delicious;

    // Empty constructor because Firebase needs this
    public Fruit() {
    }

    public Fruit(String name, boolean isDelicious) {
        this.nameOfFruit = name;
        this.delicious = isDelicious;
    }

    public String getNameOfFruit() {
        return nameOfFruit;
    }

    public boolean isDelicious() {
        return delicious;
    }
}
