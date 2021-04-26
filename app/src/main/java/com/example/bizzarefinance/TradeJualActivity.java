package com.example.bizzarefinance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class TradeJualActivity extends AppCompatActivity {

    private TextView txtCName1, txtTJPrice;
    private EditText editTextAmountBuy;
    private ImageView CIcon;
    private Button btnTJ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade_jual);

        CIcon = findViewById(R.id.TJIcon);
        txtCName1 = findViewById(R.id.txtTJIconName);
        txtTJPrice = findViewById(R.id.txtTJPrice);
        editTextAmountBuy = findViewById(R.id.editTextAmountBuy);
        btnTJ = findViewById(R.id.btnTJ);

        Bundle bundle = getIntent().getExtras();
        String Cname = bundle.getString("CryptoName").toString();
        int pos = Integer.parseInt(bundle.getString("position"));
        double price = Double.parseDouble(bundle.getString("Price"));
        String name = bundle.getString("CName");
        MarketActivity ma = new MarketActivity();
        if (bundle != null) {
            CIcon.setImageResource(ma.getImgs(pos));
            txtCName1.setText(ma.getTitles(pos));
            txtTJPrice.setText(" = $" + ma.getPrices(pos));
        }

        btnTJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String purchase = editTextAmountBuy.getText().toString();
                double amount = Double.parseDouble(purchase);
                double totalCoin = amount/price;
                Intent intent = new Intent(TradeJualActivity.this,ConfirmBuyActivity.class);
                intent.putExtra("totalCoin",String.valueOf(totalCoin));
                intent.putExtra("purchase",purchase);
                intent.putExtra("cname",ma.getStandfors(BuySellActivity.getBuyPos()));
                startActivity(intent);
            }
        });

    }

    private TextWatcher formatWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

}