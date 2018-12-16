package com.example.shaked.firebaseapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class LeahsAdapter extends RecyclerView.Adapter<TamarsViewHolder> {

//    private String[] names;
//    private String[] country;
//
//    public LeahsAdapter(String[] names, String[] country) {
//        this.names = names;
//        this.country = country;
//    }

    // Now we're using ArrayLists instead of arrays of strings
    // which are commented out above
//    private ArrayList<String> names;
//    private ArrayList<String> countries;
//
//    public LeahsAdapter(ArrayList<String> names, ArrayList<String> countries) {
//        this.names = names;
//        this.countries = countries;
//    }


    private ArrayList<Fruit> fruits;
    private Context context;

    public LeahsAdapter(ArrayList<Fruit> fruits, Context context) {
        this.fruits = fruits;
        this.context = context;
    }

    @NonNull
    @Override
    public TamarsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View layoutView = inflater.inflate(R.layout.row_template, viewGroup, false);

        TamarsViewHolder tamarsViewHolder = new TamarsViewHolder(layoutView);

        return tamarsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TamarsViewHolder tamarsViewHolder, int i) {
//        tamarsViewHolder.header.setText(names[i]);
//        tamarsViewHolder.subHeader.setText(country[i]);


        // Accessing elements at index is done differently on ArrayLists
        // We do not use the square brackets, instead - we use get(int i)

        final Fruit runningFruit = this.fruits.get(i);


        tamarsViewHolder.header.setText(runningFruit.getNameOfFruit());
        tamarsViewHolder.subHeader.setText(runningFruit.isDelicious() + "");

        tamarsViewHolder.numberTextView.setText(i + "");

        tamarsViewHolder.header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // getting a context can easilt be done with v.getContext()

//                Log.e("AAA", "Hello you clicked on : " + runningFruit.getNameOfFruit());
                Toast.makeText(context, runningFruit.getNameOfFruit(), Toast.LENGTH_SHORT).show();
            }
        });



    }

    @Override
    public int getItemCount() {
        return fruits.size();
    }
}
