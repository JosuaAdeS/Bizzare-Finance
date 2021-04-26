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

public class ConfirmBuyActivity extends AppCompatActivity {

    private TextView txtCoin, txtAmount, txtBalance, txtTotalBalance;
    SharedPreferences sharedPreferences;
    private Button btnConfirm;
    private ImageButton btnBack;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://bizzarefinance-default-rtdb.firebaseio.com/");
    DatabaseReference reference = firebaseDatabase.getReference(user.getUid());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_buy);
        Utils utils = new Utils();
        sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);

        txtCoin = findViewById(R.id.confirmCoin);
        txtAmount = findViewById(R.id.conformAmount);
        txtBalance = findViewById(R.id.confirmBalance);
        txtTotalBalance = findViewById(R.id.confirmTotal);
        btnConfirm = findViewById(R.id.btnConfirm);
        btnBack = findViewById(R.id.backConfirm);

        String coin = getIntent().getStringExtra("totalCoin");
        String purchase = getIntent().getStringExtra("purchase");
        String Cname = getIntent().getStringExtra("cname");
        int balance = sharedPreferences.getInt("balance", 0);
        int totalBalance = balance - (Integer.parseInt(purchase));

        Log.d("Preferences : ", balance + "");

        txtCoin.setText(coin.substring(0, 5).replace(".", ",") + " " + Cname);
        txtAmount.setText(String.format("$%,d", Integer.parseInt(purchase)));
        txtBalance.setText(String.format("$%,d", balance));
        txtTotalBalance.setText(String.format("$%,d", totalBalance));

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                utils.getReferenceFirebase().child("balance").setValue(totalBalance).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Intent intent = new Intent(ConfirmBuyActivity.this, WalletActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });
    }
}