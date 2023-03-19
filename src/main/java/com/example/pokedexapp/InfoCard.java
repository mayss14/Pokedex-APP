package com.example.pokedexapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class InfoCard extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_card);

        ImageView i1;
        TextView t1,w,h;
        LinearProgressIndicator bar1,bar2,bar3,bar4,bar5;

        i1= (ImageView) findViewById(R.id.imagePoke);
        t1= (TextView) findViewById(R.id.title);
        w= (TextView) findViewById(R.id.weight);
        h= (TextView) findViewById(R.id.height);
        bar1= (LinearProgressIndicator) findViewById(R.id.b1);
        bar2= (LinearProgressIndicator) findViewById(R.id.b2);
        bar3= (LinearProgressIndicator) findViewById(R.id.b3);
        bar4= (LinearProgressIndicator) findViewById(R.id.b4);
        bar5= (LinearProgressIndicator) findViewById(R.id.b5);


        // to read json file data
        try{

            InputStream is = getAssets().open("pokedex.json");
            int size= is.available();
            byte[] buf= new byte[size];
            is.read(buf);
            is.close();
            String json;
            json = new String(buf, StandardCharsets.UTF_8);
            JSONArray jarray= new JSONArray(json);

            //get the parameters
            Intent myIntent = getIntent();
            int id = myIntent.getIntExtra("id",0);

            JSONObject jo= jarray.getJSONObject(id);

            t1.setText(jo.getString("name"));
            loadImage(jo.getString("image"),i1);
            w.setText(jo.getString("weight"));
            h.setText(jo.getString("height"));
            bar1.setProgressCompat(jo.getInt("HP"),true);
            bar2.setProgressCompat(jo.getInt("Attack"),true);
            bar3.setProgressCompat(jo.getInt("Defense"),true);
            bar4.setProgressCompat(jo.getInt("Sp. Attack"),true);
            bar5.setProgressCompat(jo.getInt("Speed"),true);



        } catch (Exception e) {
            e.printStackTrace();
        }



    }
    private void loadImage(String url, ImageView i) {
        Picasso.get()
                .load(url)
                .into(i);
    }
}