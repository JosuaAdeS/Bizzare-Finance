package com.example.bizzarefinance;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class BuySellActivity extends AppCompatActivity {

    ListView list;
    public static int buyPos = 0;
    private ImageButton back;

    MarketActivity ma = new MarketActivity();

    String[] TCnames = ma.getTitles();
    String[] TPrices = ma.getPrices();
    int[] TLogos = ma.getImgs();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_sell);

        list = findViewById(R.id.listCrypto);
        MyAdapter adapter = new MyAdapter(this, TCnames, TLogos);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent buy = new Intent(BuySellActivity.this, TradeJualActivity.class);
                buy.putExtra("CryptoName", list.getItemAtPosition(position).toString());
                buy.putExtra("position", String.valueOf(position));
                buy.putExtra("Price", TPrices[position].replaceAll(",", ""));
                buy.putExtra("CName", TCnames[position]);
                setBuyPos(position);
                startActivity(buy);
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

    public static int getBuyPos() {
        return buyPos;
    }

    public static void setBuyPos(int buyPos) {
        BuySellActivity.buyPos = buyPos;
    }
}