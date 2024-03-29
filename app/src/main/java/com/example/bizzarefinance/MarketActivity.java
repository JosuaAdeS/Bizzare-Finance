package com.example.bizzarefinance;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MarketActivity extends AppCompatActivity {

    ListView list;
    public static int pos;


    String titles[] = {"ASCH Finance","Bitcoin", "Faircoin", "Myth US", "PeerNance", "Radium", "Redcoin", "RiseCoin", "Skylink", "Vergecoin", "Voxels", "World Link"};
    String standfors [] = { "ASCH","BTC", "FCN", "MYU", "PNC", "RAD", "RDC", "RSC", "SLK", "VEC", "VXL", "WLK"};
    String prices [] = {"2,195.19","49,732.72","488.10","1.01","0.27676","29.14","224.97","31.19","3,852.62","39,122.51","254.78","769,90"};
    String changes [] = {"-6.63%","-0,72%","-1.81%","-4.17%","-6.95%","+0.04%","+14.66%","-5.14%","+3.16%","-7.83%","-3.84%","+3.54%"};
    int imgs[] = {R.drawable.aschfinance,R.drawable.bitcoin,R.drawable.faircoin,R.drawable.mythus,
            R.drawable.peernance,R.drawable.radium,R.drawable.redcoin,R.drawable.risecoin,
            R.drawable.skylink,R.drawable.vergecoin,R.drawable.voxels,R.drawable.worldlink};

    private ImageButton navTrade,navWallet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);

        list = findViewById(R.id.listMarket);
        navTrade = findViewById(R.id.navTrade1);
        navWallet = findViewById(R.id.navWallet1);

        MyAdapter adapter = new MyAdapter(this, titles, standfors, prices, changes, imgs);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MarketActivity.this,MarketActivity2.class);
                intent.putExtra("CryptoName",list.getItemAtPosition(position).toString());
                setPos(position);
                startActivity(intent);
            }
        });
        list.setAdapter(adapter);

        navTrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MarketActivity.this,TradeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        navWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MarketActivity.this,WalletActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    class MyAdapter extends ArrayAdapter<String> {
        Context context;
        String myTitles[];
        String myStandfors[];
        String myprices[];
        String mychanges[];
        int[] imgs;

        MyAdapter (Context c, String[] titles, String[] standfors, String[] prices, String[] changes, int[] imgs){
            super(c,R.layout.row,R.id.txtCname,titles);
            this.context = c;
            this.myTitles = titles;
            this.myStandfors = standfors;
            this.myprices = prices;
            this.mychanges = changes;
            this.imgs = imgs;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row, parent,false);
            ImageView images = row.findViewById(R.id.logo1);
            TextView myTitle = row.findViewById(R.id.txtCname);
            TextView myStandfor = row.findViewById(R.id.txtCne);
            TextView myPrice = row.findViewById(R.id.txtPrice);
            TextView myChange = row.findViewById(R.id.txtChange);

            int x = changes[position].indexOf("+");
            boolean color;
            if (x==0)color = true;
            else color = false;

            images.setImageResource(imgs[position]);
            myTitle.setText(titles[position]);
            myStandfor.setText(standfors[position]);
            myPrice.setText("$"+prices[position]);

            if (color==false){
                myChange.setTextColor(getResources().getColor(R.color.red));
                myChange.setText(changes[position]);
            }else {
                myChange.setTextColor(getResources().getColor(R.color.green));
                myChange.setText(changes[position]);
            }

            return row;
        }
    }


    public String getTitles(int i) {
        return titles[i];
    }

    public String getStandfors(int i) {
        return standfors[i];
    }

    public String getPrices(int i) {
        return prices[i];
    }

    public String getChanges(int i) {
        return changes[i];
    }

    public int getImgs(int i) {
        return imgs[i];
    }

    public ListView getList() {
        return list;
    }

    public String[] getTitles() {
        return titles;
    }

    public String[] getStandfors() {
        return standfors;
    }

    public String[] getPrices() {
        return prices;
    }

    public String[] getChanges() {
        return changes;
    }

    public int[] getImgs() {
        return imgs;
    }

    public static int getPos() {
        return pos;
    }

    public static void setPos(int pos) {
        MarketActivity.pos = pos;
    }
}