package com.example.bizzarefinance;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
    private Button btnTJ, maksBalance;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade_jual);

        CIcon = findViewById(R.id.TJIcon);
        txtCName1 = findViewById(R.id.txtTJIconName);
        txtTJPrice = findViewById(R.id.txtTJPrice);
        editTextAmountBuy = findViewById(R.id.editTextAmountBuy);
        btnTJ = findViewById(R.id.btnTJ);
        maksBalance = findViewById(R.id.maksBalance);

        sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);

        int balance = sharedPreferences.getInt("balance", 0);
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

        maksBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextAmountBuy.setText(balance + "");
            }
        });
        btnTJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String purchase = editTextAmountBuy.getText().toString();
                double amount = Double.parseDouble(purchase);
                if (amount <= balance) {
                    if (amount > 10) {
                        double totalCoin = amount / price;
                        Intent intent = new Intent(TradeJualActivity.this, ConfirmBuyActivity.class);
                        intent.putExtra("totalCoin", String.valueOf(totalCoin));
                        intent.putExtra("purchase", purchase);

                        intent.putExtra("cname", ma.getStandfors(BuySellActivity.getBuyPos()));

                        startActivity(intent);
                    }else {
                        AlertDialog alertDialog = new AlertDialog.Builder(TradeJualActivity.this).create();
                        alertDialog.setTitle("Alert");
                        alertDialog.setMessage("You must purchase at least $10");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();
                    }
                } else {
                    AlertDialog alertDialog = new AlertDialog.Builder(TradeJualActivity.this).create();
                    alertDialog.setTitle("Alert");
                    alertDialog.setMessage("You can't buy more than your balance");
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