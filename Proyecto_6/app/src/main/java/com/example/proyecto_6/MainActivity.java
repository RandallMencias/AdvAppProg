package com.example.proyecto_6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ImageView iv = (ImageView) findViewById(R.id.imageView1);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MainActivity", "onClick: ");
//                new LoadImageTask(iv).execute("https://d2pn8kiwq2w21t.cloudfront.net/original_images/jpegPIA04628.jpg");

//                new html("https://nasasearch.nasa.gov/search?affiliate=nasa&page=6&query=%2A.jpg&sort_by=&utf8=%E2%9C%93").execute();
//
                new url("https://www.jpl.nasa.gov/images/pia04628-galaxy-messier-51").execute();
            }

        });



    }



}
