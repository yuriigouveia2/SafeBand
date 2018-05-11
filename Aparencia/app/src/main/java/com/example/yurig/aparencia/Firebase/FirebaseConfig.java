package com.example.yurig.aparencia.Firebase;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FirebaseConfig {
    private FirebaseDatabase database;
    private DatabaseReference mref;
    public DatabaseReference child;

    public FirebaseConfig(){
        database = FirebaseDatabase.getInstance();
        mref = database.getReferenceFromUrl("https://onyx-silo-199918.firebaseio.com/");
    }
}
