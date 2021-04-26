package com.example.bizzarefinance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class TradeBeliActivity extends AppCompatActivity {

    private TextView txtCName1, txtTBPrice, txtCname2;
    private EditText editTextAmountSell;
    private ImageView CIcon;
    private Button btnTB,maks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade_beli);

        CIcon = findViewById(R.id.TBIcon);
        txtCName1 = findViewById(R.id.txtTBIconName);
        txtTBPrice = findViewById(R.id.txtTBPrice);
        editTextAmountSell = findViewById(R.id.editTextAmountSell);
        btnTB = findViewById(R.id.btnSell);
        maks = findViewById(R.id.maks);
        txtCname2 = findViewById(R.id.txtTBIconName2);

        Bundle bundle = getIntent().getExtras();
        String Cname = bundle.getString("CryptoName").toString();
        String TotalCoin = bundle.getString("TotalCoin");
        int pos = Integer.parseInt(bundle.getString("position"));
        double price = Double.parseDouble(bundle.getString("Price"));
        String name = bundle.getString("CName");
        MarketActivity ma = new MarketActivity();
        if (bundle != null) {
            CIcon.setImageResource(ma.getImgs(pos));
            txtCName1.setText(name);
            txtTBPrice.setText(" = $" + ma.getPrices(pos));
            txtCname2.setText(name);
        }

        maks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextAmountSell.setText(TotalCoin);
            }
        });

    }
}