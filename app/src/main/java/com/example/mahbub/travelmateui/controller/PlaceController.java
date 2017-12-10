package com.example.mahbub.travelmateui.controller;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.mahbub.travelmateui.MainActivity;
import com.example.mahbub.travelmateui.model.PlaceModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

/**
 * Created by MAHBUB on 10-Dec-17.
 */

public class PlaceController {

    Context context;
    // Write a message to the database
    FirebaseDatabase database;
    DatabaseReference placeReference;

    public static PlaceModel currentPlaceModel = null;

    public PlaceController(Context contex) {
        this.context = contex;
        database = FirebaseDatabase.getInstance();
        // get reference to 'places' node
        placeReference = database.getReference("places");
    }

    public void savePlace(PlaceModel placeModel){
        //getting a unique id using push().getKey() method
        //it will create a unique id and we will use it as the Primary Key for our Artist
        String id = placeReference.push().getKey();

        placeModel.setPlaceId(id);
        placeReference.child(id).setValue(placeModel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(context, "Place Saved", Toast.LENGTH_SHORT).show();
                    ((AppCompatActivity)context).finish();
                } else {
                    // If fails
                    Toast.makeText(context, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public PlaceModel getPlaceModelById(String placeid){
        placeReference.child(placeid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                PlaceModel placeModel = dataSnapshot.getValue(PlaceModel.class);
                currentPlaceModel = placeModel;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // If cancelled
                Toast.makeText(context, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                return;
            }
        });
        return currentPlaceModel;
    }
}
