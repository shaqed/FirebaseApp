package com.example.shaked.firebaseapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        ArrayList<Fruit> fruits = new ArrayList<>();
        fruits.add(new Fruit("Orange", true));
        fruits.add(new Fruit("Banana", true));
        fruits.add(new Fruit("Guava", false));


        RecyclerView recyclerView = findViewById(R.id.secondRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        LeahsAdapter newAdapter = new LeahsAdapter(fruits);

        recyclerView.setAdapter(newAdapter);

    }
}
