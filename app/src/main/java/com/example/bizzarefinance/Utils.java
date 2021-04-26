package com.example.bizzarefinance;

import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Utils {

    public DatabaseReference getReferenceFirebase() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://bizzarefinance-default-rtdb.firebaseio.com/");
        DatabaseReference reference = firebaseDatabase.getReference(user.getUid());
        return reference;
    }

}
