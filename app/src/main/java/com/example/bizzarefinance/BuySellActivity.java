package com.example.bizzarefinance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class BuySellActivity extends AppCompatActivity {

    ListView list;
    String titles[] = {"Bitcoin", "ASCH Finance", "Faircoin", "Myth US", "PeerNance", "Radium", "Redcoin", "RiseCoin", "Skylink", "Vergecoin", "Voxels", "World Link"};
    String standfors [] = {"BTC", "ASCH", "FCN", "MYU", "PNC", "RAD", "RDC", "RSC", "SLK", "VEC", "VXL", "WLK"};
    int imgs[] = {R.drawable.bitcoin,R.drawable.aschfinance,R.drawable.faircoin,R.drawable.mythus,
            R.drawable.peernance,R.drawable.radium,R.drawable.redcoin,R.drawable.risecoin,
            R.drawable.skylink,R.drawable.vergecoin,R.drawable.voxels,R.drawable.worldlink};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_sell);

    }
}