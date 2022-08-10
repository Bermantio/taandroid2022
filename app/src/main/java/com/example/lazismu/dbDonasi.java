package com.example.lazismu;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class dbDonasi {
    private DatabaseReference databaseReference;
    public dbDonasi(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(DonasiInput.class.getSimpleName());
    }
    public Task<Void> add(DonasiInput donasi)
    {
        return databaseReference.push().setValue(donasi);
    }
}
