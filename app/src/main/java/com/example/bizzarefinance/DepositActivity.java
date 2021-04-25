package com.example.bizzarefinance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);

        String userId = UserDetail.getUid();

        amountDeposit = findViewById(R.id.amountDeposit);
        confirmDeposit = findViewById(R.id.confirmDeposit);
        depositBack = findViewById(R.id.depositBack);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://bizzarefinance-default-rtdb.firebaseio.com/");
        DatabaseReference getReference = firebaseDatabase.getReference(userId);

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
                int temp = WalletActivity.getBalance();
                int amount = Integer.parseInt(amountDeposit.getText().toString()) + temp;
                if (amount<=10000){
                    Toast.makeText(DepositActivity.this, "Deposit minimum is 10,000",
                            Toast.LENGTH_SHORT).show();
                }else {
                    getReference.child("balance").setValue(amount)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(DepositActivity.this, "Deposit Successed",
                                            Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(DepositActivity.this,WalletActivity.class);
                                    startActivity(intent);
                                }
                            });
                }
            }
        });
    }
}