package com.example.bizzarefinance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class TradeJualActivity extends AppCompatActivity {

    private TextView txtCName1, txtCname2, txtTJPrice, txtAmounCryptoBuy;
    private EditText editTextAmountBuy;
    private ImageView CIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade_jual);

        CIcon = findViewById(R.id.TJIcon);
        txtCName1 = findViewById(R.id.txtTJIconName);
        txtTJPrice = findViewById(R.id.txtTJPrice);
        editTextAmountBuy = findViewById(R.id.editTextAmountBuy);
        txtAmounCryptoBuy = findViewById(R.id.txtTJAmountCryptoBuy);
        txtCname2 = findViewById(R.id.txtTJIconName2);

        try {
            Bundle bundle = getIntent().getExtras();
            String Cname = bundle.getString("CryptoName").toString();
            int pos = Integer.parseInt(bundle.getString("position"));
            MarketActivity ma = new MarketActivity();
            BuySellActivity bs = new BuySellActivity();
            if (bundle != null) {
                CIcon.setImageResource(ma.getImgs(pos));
                txtCName1.setText(ma.getTitles(pos));
                txtTJPrice.setText(" = $" + ma.getPrices(pos));
                txtCname2.setText(ma.getTitles(pos));
            }
        } catch (Exception e) {
            Log.w("Error Trade Jual : ?", e);
        }

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