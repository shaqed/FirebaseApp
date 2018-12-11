package com.example.shaked.firebaseapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    FirebaseAuth auth;
    private FirebaseDatabase database;

    private RecyclerView recyclerView;

    private ArrayList<Fruit> jamesFavoriteFruits; // = null by default


    private void login(String email, String password) {
        auth = FirebaseAuth.getInstance(); // this is like firebase.auth()


        // After sending the request, we get back a Task<AuthResult> object
        // we will use the task variable to set a function that
        // will trigger when the request is complete
        // this is equivalent to the then() method from JavaScript
        Task<AuthResult> task = auth.signInWithEmailAndPassword(email, password);


        // this is equivalent to
        // auth.signInWithEmailAndPassword(email,password).then(function(res) {
        // });
        task.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                // To check if the task is successful
                // we use the task.isSuccessful() function
                boolean isSuccessful = task.isSuccessful();

                if (isSuccessful == true) {
                    Log.e("ABC", "Logged in successfully");
                } else {
                    Log.e("ABC", "Login failed");
                    // Information why our task failed can be found from the Exception object
                    Exception exception = task.getException();

                    String errorCause = exception.getMessage();
                    // print errorCause
                }
            }
        });

        // NOTE: Here again, our login() method does not wait for the request
        // to be complete.
        // so the login() function does not return true / false
        // you will need to define some behavior that will run when the request
        // will be complete to decide what to do
    }


    public void register(String email, String password) {
        // the register process is almost identical to the login()

        // Get the auth object
        auth = FirebaseAuth.getInstance();

        // Call the function and get back a Task
        Task<AuthResult> retTask = auth.createUserWithEmailAndPassword(email, password);

        // Set an onCompleteListener
        retTask.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // successful register
                } else {
                    // unsuccessful register
                    String errorCause = task.getException().getMessage();


                }
            }
        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        // initialized the array list
        jamesFavoriteFruits = new ArrayList<>(); // do not forget this line

        findViewById(R.id.addButton).setOnClickListener(this);


        // get recycler view
        recyclerView = findViewById(R.id.myRecycler);

        // set layout manager

        // Create a linear layout manager
        LinearLayoutManager llm = new LinearLayoutManager(this);
        // Set the layout manager of the recycler view
        recyclerView.setLayoutManager(llm);


        // for now, recycler view's adapter is initialized
        // with an empty list of fruits
        LeahsAdapter adapter = new LeahsAdapter(jamesFavoriteFruits);
        recyclerView.setAdapter(adapter);


        // Set the database observer
        DatabaseReference dbRef = database.getReference("/Fruits");

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                jamesFavoriteFruits.clear();
                for (DataSnapshot childSnap : dataSnapshot.getChildren()){

                    // Get data from the snapshot
                    String nameOfFruit = childSnap.child("nameOfFruit").getValue(String.class);
                    boolean isDelicious = childSnap.child("delicious").getValue(Boolean.class);

                    // Construct a new fruit
                    Fruit newFruitFromFirebase = new Fruit(nameOfFruit, isDelicious);

                    // Add the fruit to the ArrayList
                    jamesFavoriteFruits.add(newFruitFromFirebase);

                }
                // after done adding everything to the list
                // notify the adapter of the recycler view
                recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }

    @Override
    public void onClick(View v) {
        // add button was clicked

        // Strings from EditTexts:
        EditText nameEditText = findViewById(R.id.nameEditText);
        String nameOfFruit = nameEditText.getText().toString();

        EditText countryEditText = findViewById(R.id.countryEditText);
        String countryString = countryEditText.getText().toString();


        nameEditText.setText("");
        countryEditText.setText("");

        DatabaseReference newFruitChild = database.getReference("/Fruits").push();
        Fruit newFruit = new Fruit(nameOfFruit, true);


        newFruitChild.setValue(newFruit);






    }
}
