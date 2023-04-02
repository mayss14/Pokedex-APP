package com.example.pokedexapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class InfoCard extends AppCompatActivity {

    ImageView i1;
    TextView t1, w, h;
    LinearProgressIndicator bar1, bar2, bar3, bar4, bar5;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_card);


        i1 = (ImageView) findViewById(R.id.imagePoke);
        t1 = (TextView) findViewById(R.id.title);
        w = (TextView) findViewById(R.id.weight);
        h = (TextView) findViewById(R.id.height);
        bar1 = (LinearProgressIndicator) findViewById(R.id.b1);
        bar2 = (LinearProgressIndicator) findViewById(R.id.b2);
        bar3 = (LinearProgressIndicator) findViewById(R.id.b3);
        bar4 = (LinearProgressIndicator) findViewById(R.id.b4);
        bar5 = (LinearProgressIndicator) findViewById(R.id.b5);
        String URL = "";

        //get the parameters
        Intent myIntent = getIntent();


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String name = bundle.getString("name");
            int id = bundle.getInt("id");
            loadImage("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + id + ".png", i1);
            t1.setText(name);

            URL = "https://pokeapi.co/api/v2/pokemon/" + id;

            // parsing the api

            StringRequest sr = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        JSONObject jo = new JSONObject(response);
                        w.setText(jo.getString("weight"));
                        h.setText(jo.getString("height"));
                        JSONArray stats = jo.getJSONArray("stats");
                        int i=0;
                        bar1.setProgressCompat(stats.getJSONObject(i).getInt("base_stat"),true);
                        bar2.setProgressCompat(stats.getJSONObject(i+1).getInt("base_stat"),true);
                        bar3.setProgressCompat(stats.getJSONObject(i+1).getInt("base_stat"),true);
                        bar4.setProgressCompat(stats.getJSONObject(i+4).getInt("base_stat"),true);
                        bar5.setProgressCompat(stats.getJSONObject(i+5).getInt("base_stat"),true);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }
            );
            RequestQueue rq = Volley.newRequestQueue(this);
            rq.add(sr);

                /*
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

            */

        }
    }

    private void loadImage(String url, ImageView i) {
        Picasso.get()
                .load(url)
                .into(i);
    }
}