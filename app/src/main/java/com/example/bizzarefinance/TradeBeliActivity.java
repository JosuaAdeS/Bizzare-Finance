package com.example.bizzarefinance;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class TradeBeliActivity extends AppCompatActivity {

    private TextView txtCName1, txtTBPrice, txtCname2;
    private EditText editTextAmountSell;
    private ImageView CIcon;
    private Button btnTB, maks;

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
            txtCname2.setText(ma.getStandfors(pos) + "");
        }

        maks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextAmountSell.setText(TotalCoin);
            }
        });

        btnTB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String purchase = editTextAmountSell.getText().toString();
                double amount = Double.parseDouble(purchase);
                double coin = Double.parseDouble(TotalCoin);
                if (amount <= coin && amount > 0) {
                    double totalPrice = amount * price;
                    int totalPrice2 = (int) totalPrice;
                    double remainCoin = coin-amount;
                    Log.d("Intent tes : ",totalPrice2 + remainCoin + purchase);
                    Intent intent = new Intent(TradeBeliActivity.this, ConfirmSell.class);
                    intent.putExtra("totalPrice", String.valueOf(totalPrice2));
                    intent.putExtra("remainCoin", String.valueOf(remainCoin));
                    intent.putExtra("purchase", purchase);
                    intent.putExtra("cname", ma.getStandfors(pos));
                    startActivity(intent);
                }else {
                    AlertDialog alertDialog = new AlertDialog.Builder(TradeBeliActivity.this).create();
                    alertDialog.setTitle("Alert");
                    alertDialog.setMessage("Amount must more than 0 and can't more than coin that you have");
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
}