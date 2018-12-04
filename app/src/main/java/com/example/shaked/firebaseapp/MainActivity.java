package com.example.shaked.firebaseapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    FirebaseAuth auth;


    private RecyclerView recyclerView;

    public String[] names = {"James", "Ben", "Leah", "Jay", "Kayanna", "Tamar", "Jacob", "Sivan"
            , "Jared", "Dylan", "Jonathan", "Jethro", "Geffen"};
    public String[] country = {"UK", "Canada", "Miami", "Australia", "Texas", "New-York", "Miami", "Yavne"
            , "Chicago", "Georgia", "Queens", "South Africa", "San Francisco"};


    private ArrayList<String> namesArrayList;
    private ArrayList<String> countriesArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();

        findViewById(R.id.addButton).setOnClickListener(this);

        // shortcut for converting arrays to ArrayLists
        namesArrayList = new ArrayList<>(Arrays.asList(names));
        countriesArrayList = new ArrayList<>(Arrays.asList(country));



        // get recycler view
        recyclerView = findViewById(R.id.myRecycler);

        // set layout manager

        // Create a linear layout manager
        LinearLayoutManager llm = new LinearLayoutManager(this);
        // Set the layout manager of the recycler view
        recyclerView.setLayoutManager(llm);

        // set an adapter

        // Create an object of the adapter we created
        LeahsAdapter leahsAdapter = new LeahsAdapter(this.namesArrayList, this.countriesArrayList);
        // Set the adapter of the recycler view to be leah's
        recyclerView.setAdapter(leahsAdapter);


    }

    @Override
    public void onClick(View v) {
        // add button was clicked

        // Strings from EditTexts:
        EditText nameEditText = findViewById(R.id.nameEditText);
        String nameString = nameEditText.getText().toString();

        EditText countryEditText = findViewById(R.id.countryEditText);
        String countryString = countryEditText.getText().toString();


        nameEditText.setText("");
        countryEditText.setText("");


        // Add the strings to the array lists
        this.namesArrayList.add(nameString);
        this.countriesArrayList.add(countryString);

//        // Re-create the Adapter (not efficient, will be removed in the future)
//        LeahsAdapter adapter = new LeahsAdapter(this.namesArrayList, this.countriesArrayList);
//
//        // Set the RecyclerView's adapter again to the new one
//        this.recyclerView.setAdapter(adapter);

        // Better way to update the view
        recyclerView.getAdapter().notifyDataSetChanged();


        Toast.makeText(this, "New Item Added!", Toast.LENGTH_SHORT).show();

    }
}
