package com.example.bizzarefinance;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SellActivity extends AppCompatActivity {

    ListView list;
    public static int sellPos = 0;

    MarketActivity ma = new MarketActivity();

    String[] TCnames = ma.getTitles();
    String[] TPrices = ma.getPrices();
    String[] TStandfors = ma.getStandfors();
    int[] TLogos = ma.getImgs();
    Utils utils = new Utils();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_buy);

        list = findViewById(R.id.listCrypto2);

        MyAdapter adapter = new MyAdapter(this, TCnames, TLogos);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                DatabaseReference rootRef = utils.getReferenceFirebase();
                DatabaseReference cryptoRef = rootRef.child("crypto");
                ValueEventListener eventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        List<String> cryptoes = new ArrayList<>();
                        List<String> cryptoes2 = new ArrayList<>();
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            String crypto = ds.getKey();
                            String crypto2 = ds.getValue().toString();
                            cryptoes.add(crypto);
                            cryptoes2.add(crypto2);
                        }
                        int s = 0;
                        String total = "";
                        for (int i = 0; i < cryptoes.size(); i++) {
                            if (cryptoes.get(i).equalsIgnoreCase(TStandfors[position])) {
                                total = cryptoes2.get(i);
                                s++;
                            }
                        }
                        if (s > 0) {
                            Intent sell = new Intent(SellActivity.this, TradeBeliActivity.class);
                            sell.putExtra("CryptoName", list.getItemAtPosition(position).toString());
                            sell.putExtra("position", String.valueOf(position));
                            sell.putExtra("Price", TPrices[position].replaceAll(",", ""));
                            sell.putExtra("CName", TCnames[position]);
                            sell.putExtra("TotalCoin",total);
                            setsellPos(position);
                            startActivity(sell);
                        } else {
                            AlertDialog alertDialog = new AlertDialog.Builder(SellActivity.this).create();
                            alertDialog.setTitle("Alert");
                            alertDialog.setMessage("You don't have Asset in this Crypto Coin");
                            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                            alertDialog.show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                };
                cryptoRef.addListenerForSingleValueEvent(eventListener);


            }
        });
        list.setAdapter(adapter);
    }

    class MyAdapter extends ArrayAdapter<String> {
        Context context;
        String myTitles[];
        int[] imgs;

        MyAdapter(Context c, String[] titles, int[] imgs) {
            super(c, R.layout.row_trade, R.id.txtTCname, titles);
            this.context = c;
            this.myTitles = titles;
            this.imgs = imgs;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row_trade, parent, false);
            ImageView images = row.findViewById(R.id.TLogo);
            TextView myTitle = row.findViewById(R.id.txtTCname);

            images.setImageResource(TLogos[position]);
            myTitle.setText(TCnames[position]);

            return row;
        }
    }

    public static int getSellPos() {
        return sellPos;
    }

    public static void setsellPos(int sellPosPos) {
        SellActivity.sellPos = sellPos;
    }
}