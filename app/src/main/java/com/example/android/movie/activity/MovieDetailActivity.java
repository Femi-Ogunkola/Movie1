package com.example.android.movie.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.movie.R;
import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Intent intent = getIntent();
        String Overview = intent.getStringExtra("Overview");
        String Title = intent.getStringExtra("Title");
        String Date = intent.getStringExtra("Date");
        String Poster = intent.getStringExtra("Poster");

        //Bitmap bitmap = BitmapFactory.decodeFile(Poster);

        ImageView  img =(ImageView)findViewById(R.id.poster);

       // img.setImageBitmap(bitmap);

        Picasso.with(this).load("https://image.tmdb.org/t/p/original"+Poster).centerInside().resize(300,450)
                .placeholder(R.drawable.ic_launcher_background).into(img);





        TextView textView = (TextView)findViewById(R.id.text3);
        textView.setText(Overview);

        TextView textView1 = findViewById(R.id.text2);
        textView1.setText(Date);

        TextView textView2 = findViewById(R.id.text1);
        textView2.setText(Title);

       // setContentView(textView);
    }
}
