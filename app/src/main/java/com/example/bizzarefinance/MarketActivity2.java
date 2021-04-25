package com.example.bizzarefinance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MarketActivity2 extends AppCompatActivity {
    private TextView txtCnameMarket2, txtPriceMarket2, txtChangeMarket2;
    private ImageButton backMarket2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market2);

        backMarket2 = findViewById(R.id.backMarket2);
        txtChangeMarket2 = findViewById(R.id.txtChangeMarket2);
        txtCnameMarket2 = findViewById(R.id.txtCnameMarket2);
        txtPriceMarket2 = findViewById(R.id.txtPriceMarket2);
        try {
            Bundle bundle = getIntent().getExtras();
            String Cname = bundle.getString("CryptoName").toString();
            MarketActivity marketActivity = new MarketActivity();

            if (bundle != null) {
                txtCnameMarket2.setText(marketActivity.getTitles(MarketActivity.getPos()));
                txtPriceMarket2.setText(marketActivity.getPrices(MarketActivity.getPos()));
                txtChangeMarket2.setText(marketActivity.getChanges(MarketActivity.getPos()));
            }
        }catch (Exception e){
            Log.w("ErrorMarket:?",e);
        }

        backMarket2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MarketActivity2.this,MarketActivity.class);
                startActivity(intent);
            }
        });
    }
}