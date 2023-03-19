package com.example.pokedexapp;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CardView c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14,c15,c16;
        TextView t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,t14,t15,t16;
        ImageView i1,i2,i3,i4,i5,i6,i7,i8,i9,i10,i11,i12,i13,i14,i15,i16;

        CardView [] cards = {
                c1=(CardView) findViewById(R.id.card1),
                c2=(CardView) findViewById(R.id.card2),
                c3=(CardView) findViewById(R.id.card3),
                c4=(CardView) findViewById(R.id.card4),
                c5=(CardView) findViewById(R.id.card5),
                c6=(CardView) findViewById(R.id.card6),
                c7=(CardView) findViewById(R.id.card7),
                c8=(CardView) findViewById(R.id.card8),
                c9=(CardView) findViewById(R.id.card9),
                c10=(CardView) findViewById(R.id.card10),
                c11=(CardView) findViewById(R.id.card11),
                c12=(CardView) findViewById(R.id.card12),
                c13=(CardView) findViewById(R.id.card13),
                c14=(CardView) findViewById(R.id.card14),
                c15=(CardView) findViewById(R.id.card15),
                c16=(CardView) findViewById(R.id.card16),

        };

        ImageView [] images = {
                i1= (ImageView) findViewById(R.id.imageView1),
                i2= (ImageView) findViewById(R.id.imageView2),
                i3= (ImageView) findViewById(R.id.imageView3),
                i4= (ImageView) findViewById(R.id.imageView4),
                i5= (ImageView) findViewById(R.id.imageView5),
                i6= (ImageView) findViewById(R.id.imageView6),
                i7= (ImageView) findViewById(R.id.imageView7),
                i8= (ImageView) findViewById(R.id.imageView8),
                i9= (ImageView) findViewById(R.id.imageView9),
                i10= (ImageView) findViewById(R.id.imageView10),
                i11= (ImageView) findViewById(R.id.imageView11),
                i12= (ImageView) findViewById(R.id.imageView12),
                i13= (ImageView) findViewById(R.id.imageView13),
                i14= (ImageView) findViewById(R.id.imageView14),
                i15= (ImageView) findViewById(R.id.imageView15),
                i16= (ImageView) findViewById(R.id.imageView16)
        };
        TextView [] names = {
                t1= (TextView) findViewById(R.id.title1),
                t2= (TextView) findViewById(R.id.title2),
                t3= (TextView) findViewById(R.id.title3),
                t4= (TextView) findViewById(R.id.title4),
                t5= (TextView) findViewById(R.id.title5),
                t6= (TextView) findViewById(R.id.title6),
                t7= (TextView) findViewById(R.id.title7),
                t8= (TextView) findViewById(R.id.title8),
                t9= (TextView) findViewById(R.id.title9),
                t10= (TextView) findViewById(R.id.title10),
                t11= (TextView) findViewById(R.id.title11),
                t12= (TextView) findViewById(R.id.title12),
                t13= (TextView) findViewById(R.id.title13),
                t14= (TextView) findViewById(R.id.title14),
                t15= (TextView) findViewById(R.id.title15),
                t16= (TextView) findViewById(R.id.title16),

        };

            try{

                InputStream is = getAssets().open("pokedex.json");
                int size= is.available();
                byte[] buf= new byte[size];
                is.read(buf);
                is.close();
                String json;
                json = new String(buf, StandardCharsets.UTF_8);
                JSONArray jarray= new JSONArray(json);
                JSONObject jo;

                 for(int i=0;i<16;i++) {
                      jo = jarray.getJSONObject(i);
                     loadImage(jo.getString("image"), images[i]);
                     names[i].setText(jo.getString("name"));
                    int id= jo.getInt("id");
                     cards[i].setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View v) {
                             Intent intent = new Intent(getApplicationContext(), InfoCard.class);
                             intent.putExtra("id",id-1);
                             //finish();
                             startActivity(intent);
                         }
                     });
                 }

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