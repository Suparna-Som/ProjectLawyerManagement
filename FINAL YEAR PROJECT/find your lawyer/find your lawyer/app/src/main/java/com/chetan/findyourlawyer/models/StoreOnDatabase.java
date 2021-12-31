package com.chetan.findyourlawyer.models;

import com.chetan.findyourlawyer.central.C;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StoreOnDatabase {
    FirebaseDatabase database;
    DatabaseReference myRef;

    public StoreOnDatabase() {
        database = FirebaseDatabase.getInstance();
    }

    public void store(Model model) {
        if (C.createProfileWithClient) {
            myRef = database.getReference("Client");
            model.setLawPractices("NULL");
            myRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(model);
        } else {
            myRef = database.getReference("Lawyer");
            myRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(model);
        }
    }
}
