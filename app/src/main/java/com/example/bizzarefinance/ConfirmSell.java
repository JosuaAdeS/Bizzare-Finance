package com.example.bizzarefinance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConfirmSell extends AppCompatActivity {
    private TextView txtCoin2, txtAmount2, txtBalance2, txtTotalBalance2,txtremainCoin;
    SharedPreferences sharedPreferences;
    private Button btnConfirm2;
    private ImageButton btnBack2;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://bizzarefinance-default-rtdb.firebaseio.com/");
    DatabaseReference reference = firebaseDatabase.getReference(user.getUid());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_sell);

        Utils utils = new Utils();
        sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);

        txtCoin2 = findViewById(R.id.confirmCoin2);
        txtAmount2 = findViewById(R.id.confirmAmount2);
        txtBalance2 = findViewById(R.id.confirmBalance2);
        txtTotalBalance2 = findViewById(R.id.confirmTotal2);
        btnConfirm2 = findViewById(R.id.btnConfirm2);
        btnBack2 = findViewById(R.id.backConfirm);
        txtremainCoin = findViewById(R.id.confirmRemainCoin2);

        String price = getIntent().getStringExtra("totalPrice".replace(".",""));
        Log.d("purchase  : ",price);
        String purchase = getIntent().getStringExtra("purchase");
        Log.d("purchase 1: ",purchase);
        String Cname = getIntent().getStringExtra("cname");
        Log.d("purchase2 : ",Cname);
        String remain = getIntent().getStringExtra("remainCoin");
        Log.d("purchase3 : ",remain);
        int balance = sharedPreferences.getInt("balance", 0);
        int totalBalance = balance + (Integer.parseInt(price));
        Log.d("purchase4 : ",totalBalance+"");

        Log.d("Preferences : ", balance + "");

        txtAmount2.setText(price);
        txtBalance2.setText(String.format("$%,d", balance));
        txtTotalBalance2.setText(String.format("$%,d", totalBalance));
        txtremainCoin.setText(remain);


        btnConfirm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                utils.getReferenceFirebase().child("balance").setValue(totalBalance).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        utils.getReferenceFirebase().child("crypto").child(Cname).setValue(remain)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Intent intent = new Intent(ConfirmSell.this, WalletActivity.class);
                                        startActivity(intent);
                                    }
                                });
                    }
                });

            }
        });
    }
}