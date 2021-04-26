package com.example.bizzarefinance;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class TradeActivity extends AppCompatActivity {

    Intent buytent, market, wallettent, selltent;
    ImageButton buy, wallet, sell;
    ImageView trade;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade);

        buy = findViewById(R.id.imaget_btn_trade_buy);
        trade = findViewById(R.id.marketbtn);
        wallet = findViewById(R.id.navWallet1);
        sell = findViewById(R.id.imaget_btn_trade_sell);

        sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
        int balance = sharedPreferences.getInt("balance", 0);

        selltent = new Intent(this, BuySellActivity.class);
        buytent = new Intent(this, BuySellActivity.class);
        market = new Intent(this, MarketActivity.class);
        wallettent = new Intent(this, WalletActivity.class);

        wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(wallettent);
            }
        });

        trade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(market);
            }
        });


        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (balance >10){
                startActivity(buytent);
                }else {
                    AlertDialog alertDialog = new AlertDialog.Builder(TradeActivity.this).create();
                    alertDialog.setTitle("Please Top Up");
                    alertDialog.setMessage("You must have at least $10 ");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
            }
        });

        sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(selltent);
            }
        });

    }
}