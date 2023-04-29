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

    private singleton s = singleton.getInstance(); //instancia de la clase singleton

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new LoadData("https://nasasearch.nasa.gov/search?affiliate=nasa&page=6&query=%2A.jpg&sort_by=&utf8=%E2%9C%93").execute();


        ImageView iv = (ImageView) findViewById(R.id.imageView1);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MainActivity", "onClick: ");
                String name = s.getRandomImage();
                if (name != "Cache")
                    new LoadImageTask(iv).execute(s.getURL(name), name);
                else
                    iv.setImageBitmap(s.getBitmap(name));



            }
        });



    }



}

