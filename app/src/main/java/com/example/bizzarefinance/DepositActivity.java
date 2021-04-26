package com.example.bizzarefinance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DepositActivity extends AppCompatActivity {

    private EditText amountDeposit;
    private Button confirmDeposit;
    private ImageButton depositBack;
    SharedPreferences sharedPreferences;
    Utils utils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);

        amountDeposit = findViewById(R.id.amountDeposit);
        confirmDeposit = findViewById(R.id.confirmDeposit);
        depositBack = findViewById(R.id.depositBack);
        sharedPreferences = getSharedPreferences("User",MODE_PRIVATE);
        Utils utils = new Utils();
        depositBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DepositActivity.this,WalletActivity.class);
                startActivity(intent);
            }
        });
        confirmDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int balance = sharedPreferences.getInt("balance",0);
                int amount = Integer.parseInt(amountDeposit.getText().toString());
                if (amount<10000){
                    Toast.makeText(DepositActivity.this, "Deposit minimum is 10,000",
                            Toast.LENGTH_SHORT).show();
                    reload();
                }else {
                    int total = amount + balance;
                    Log.d("Top up : ",total+"");
                    utils.getReferenceFirebase().child("balance").setValue(total).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Intent intent = new Intent(DepositActivity.this, WalletActivity.class);
                            startActivity(intent);
                        }
                    });
                }
            }
        });
    }
    private void reload(){}
}