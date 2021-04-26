package com.example.bizzarefinance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class TradeActivity extends AppCompatActivity {

    Intent buytent, market, wallettent, selltent;
    ImageButton buy, wallet, sell;
    ImageView trade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade);

        buy = findViewById(R.id.imaget_btn_trade_buy);
        trade = findViewById(R.id.marketbtn);
        wallet = findViewById(R.id.navWallet1);
        sell = findViewById(R.id.imaget_btn_trade_sell);
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
                buytent.putExtra("code","0");
                startActivity(buytent);
            }
        });

        sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selltent.putExtra("code", "1");
                startActivity(selltent);
            }
        });

    }
}