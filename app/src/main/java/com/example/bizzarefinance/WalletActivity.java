package com.example.bizzarefinance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class WalletActivity extends AppCompatActivity {
    private ImageButton navMarket,navTrade,walletDeposit;
    private TextView txtBalance;
    private DatabaseReference reference;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);

        navMarket = findViewById(R.id.navMarket2);
        navTrade = findViewById(R.id.navTrade2);
        walletDeposit = findViewById(R.id.walletDeposit);
        txtBalance = findViewById(R.id.balanceTotal);

        sharedPreferences = getSharedPreferences("User",MODE_PRIVATE);

        Log.d("Preferences : ",sharedPreferences.getInt("balance",0)+" ");

        txtBalance.setText(String.format("%,d",sharedPreferences.getInt("balance",0)));

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