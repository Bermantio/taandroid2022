package com.example.lazismu;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class DAOTransaksiNonTunai {
    private DatabaseReference databaseReference;
    public DAOTransaksiNonTunai(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(transaksinontunai.class.getSimpleName());
    }
    public Task<Void> add(transaksinontunai emp)
    {
        return databaseReference.push().setValue(emp);
    }
    public Query get()
    {
        return  databaseReference.orderByKey();
    }
}
