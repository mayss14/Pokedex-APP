package com.example.pokedexapp;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    String URL = "https://pokeapi.co/api/v2/pokemon/?offset=0&limit=20";
    CardView c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20;
    TextView t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20;
    ;
    ImageView i1, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18, i19, i20;
    Random rnd = new Random();

    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CardView[] cards = {
                c1 = (CardView) findViewById(R.id.card1),
                c2 = (CardView) findViewById(R.id.card2),
                c3 = (CardView) findViewById(R.id.card3),
                c4 = (CardView) findViewById(R.id.card4),
                c5 = (CardView) findViewById(R.id.card5),
                c6 = (CardView) findViewById(R.id.card6),
                c7 = (CardView) findViewById(R.id.card7),
                c8 = (CardView) findViewById(R.id.card8),
                c9 = (CardView) findViewById(R.id.card9),
                c10 = (CardView) findViewById(R.id.card10),
                c11 = (CardView) findViewById(R.id.card11),
                c12 = (CardView) findViewById(R.id.card12),
                c13 = (CardView) findViewById(R.id.card13),
                c14 = (CardView) findViewById(R.id.card14),
                c15 = (CardView) findViewById(R.id.card15),
                c16 = (CardView) findViewById(R.id.card16),
                c17 = (CardView) findViewById(R.id.card17),
                c18 = (CardView) findViewById(R.id.card18),
                c19 = (CardView) findViewById(R.id.card19),
                c20 = (CardView) findViewById(R.id.card20)


        };

        ImageView[] images = {
                i1 = (ImageView) findViewById(R.id.imageView1),
                i2 = (ImageView) findViewById(R.id.imageView2),
                i3 = (ImageView) findViewById(R.id.imageView3),
                i4 = (ImageView) findViewById(R.id.imageView4),
                i5 = (ImageView) findViewById(R.id.imageView5),
                i6 = (ImageView) findViewById(R.id.imageView6),
                i7 = (ImageView) findViewById(R.id.imageView7),
                i8 = (ImageView) findViewById(R.id.imageView8),
                i9 = (ImageView) findViewById(R.id.imageView9),
                i10 = (ImageView) findViewById(R.id.imageView10),
                i11 = (ImageView) findViewById(R.id.imageView11),
                i12 = (ImageView) findViewById(R.id.imageView12),
                i13 = (ImageView) findViewById(R.id.imageView13),
                i14 = (ImageView) findViewById(R.id.imageView14),
                i15 = (ImageView) findViewById(R.id.imageView15),
                i16 = (ImageView) findViewById(R.id.imageView16),
                i17 = (ImageView) findViewById(R.id.imageView17),
                i18 = (ImageView) findViewById(R.id.imageView18),
                i19 = (ImageView) findViewById(R.id.imageView19),
                i20 = (ImageView) findViewById(R.id.imageView20)
        };
        TextView[] names = {
                t1 = (TextView) findViewById(R.id.title1),
                t2 = (TextView) findViewById(R.id.title2),
                t3 = (TextView) findViewById(R.id.title3),
                t4 = (TextView) findViewById(R.id.title4),
                t5 = (TextView) findViewById(R.id.title5),
                t6 = (TextView) findViewById(R.id.title6),
                t7 = (TextView) findViewById(R.id.title7),
                t8 = (TextView) findViewById(R.id.title8),
                t9 = (TextView) findViewById(R.id.title9),
                t10 = (TextView) findViewById(R.id.title10),
                t11 = (TextView) findViewById(R.id.title11),
                t12 = (TextView) findViewById(R.id.title12),
                t13 = (TextView) findViewById(R.id.title13),
                t14 = (TextView) findViewById(R.id.title14),
                t15 = (TextView) findViewById(R.id.title15),
                t16 = (TextView) findViewById(R.id.title16),
                t17 = (TextView) findViewById(R.id.title17),
                t18 = (TextView) findViewById(R.id.title18),
                t19 = (TextView) findViewById(R.id.title19),
                t20 = (TextView) findViewById(R.id.title20),

        };
        /////////////////////////////////////////////////////////////////////
        StringRequest sr = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    //Log.e("Result : ",response);
                    try {
                        JSONObject jo = new JSONObject(response);
                        JSONArray res = jo.getJSONArray("results");
                        for(int i=0;i<20;i++) {
                            names[i].setText(res.getJSONObject(i).getString("name"));
                            loadImage("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+(i+1)+".png", images[i]);
                            cards[i].setCardBackgroundColor(Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
                            //Log.e("Result : ", res.getJSONObject(i).getString("name"));
                            String name=res.getJSONObject(i).getString("name");

                            int finalI = i;

                            cards[i].setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    Bundle bundle = new Bundle();
                                    bundle.putString("name", name);
                                    bundle.putInt("id", (finalI+1));

                                    Intent intent = new Intent(getApplicationContext(), InfoCard.class);
                                    intent.putExtras(bundle);

                                    //finish();
                                    startActivity(intent);
                                }
                            });
                        }

                    } catch (JSONException e) {
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

        }

    private void loadImage(String url, ImageView i) {
        Picasso.get()
                .load(url)
                .into(i);
    }
}