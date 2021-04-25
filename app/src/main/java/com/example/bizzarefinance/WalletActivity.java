package com.example.bizzarefinance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class WalletActivity extends AppCompatActivity {
    private ImageButton navMarket,navTrade,walletDeposit;
    private TextView txtBalance;
    private DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);

        navMarket = findViewById(R.id.navMarket2);
        navTrade = findViewById(R.id.navTrade2);
        walletDeposit = findViewById(R.id.walletDeposit);
        txtBalance = findViewById(R.id.balanceTotal);

        reference = FirebaseDatabase.getInstance("https://bizzarefinance-default-rtdb.firebaseio.com/").getReference(UserDetail.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String balance = snapshot.child("balance").getValue().toString();
                int total = Integer.parseInt(balance);
                String hasil = String.format("%,d",total) + ".00";
                txtBalance.setText(hasil);
                setBalance(total);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        navTrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WalletActivity.this,TradeActivity.class);
                startActivity(intent);
            }
        });

        navMarket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WalletActivity.this,MarketActivity.class);
                startActivity(intent);
            }
        });

        walletDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WalletActivity.this,DepositActivity.class);
                startActivity(intent);
            }
        });
    }

    public static int balance;

    public static int getBalance() {
        return balance;
    }

    public static void setBalance(int balance) {
        WalletActivity.balance = balance;
    }
}