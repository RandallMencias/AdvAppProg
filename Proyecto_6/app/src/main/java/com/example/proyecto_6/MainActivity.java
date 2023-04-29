package com.example.proyecto_6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private singleton s = singleton.getInstance(); //instancia de la clase singleton

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new LoadData("https://nasasearch.nasa.gov/search?affiliate=nasa&page=6&query=%2A.jpg&sort_by=&utf8=%E2%9C%93").execute();

        //enlazar con los componentes
        ImageView iv = (ImageView) findViewById(R.id.imageView1);
        ImageView iv2 = (ImageView) findViewById(R.id.imageView2);
        ImageView iv3 = (ImageView) findViewById(R.id.imageView3);
        ImageView iv4 = (ImageView) findViewById(R.id.imageView4);
        ImageView iv5 = (ImageView) findViewById(R.id.imageView5);
        ImageView iv6 = (ImageView) findViewById(R.id.imageView6);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MainActivity", "onClick: ");
                String name = s.getRandomImage();
                if (!s.inCache(name)) {
                    System.out.println(name);
                    new LoadImageTask(iv).execute(s.getURL(name), name);
                    s.replaceImage(1, name);

                } else {
                    System.out.println("Cache");
                    iv.setImageBitmap(s.getBitmap(name));
                    s.replaceImage(1, name);
                }
            }
        });
        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MainActivity", "onClick: ");
                String name = s.getRandomImage();
                if (!s.inCache(name)) {
                    System.out.println(name);
                    new LoadImageTask(iv2).execute(s.getURL(name), name);
                    s.replaceImage(2, name);


                } else {
                    System.out.println("Cache");
                    iv2.setImageBitmap(s.getBitmap(name));
                    s.replaceImage(2, name);
                }

            }
        });
        iv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MainActivity", "onClick: ");
                String name = s.getRandomImage();
                if (!s.inCache(name)) {
                    System.out.println(name);
                    new LoadImageTask(iv3).execute(s.getURL(name), name);
                    s.replaceImage(3, name);


                } else {
                    System.out.println("Cache");
                    iv3.setImageBitmap(s.getBitmap(name));
                    s.replaceImage(3, name);
                }

            }
        });
        iv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MainActivity", "onClick: ");
                String name = s.getRandomImage();
                if (!s.inCache(name)) {
                    System.out.println(name);
                    new LoadImageTask(iv4).execute(s.getURL(name), name);
                    s.replaceImage(4, name);


                } else {
                    System.out.println("Cache");
                    iv4.setImageBitmap(s.getBitmap(name));
                    s.replaceImage(4, name);
                }

            }
        });
        iv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MainActivity", "onClick: ");
                String name = s.getRandomImage();
                if (!s.inCache(name)) {
                    System.out.println(name);
                    new LoadImageTask(iv5).execute(s.getURL(name), name);
                    s.replaceImage(5, name);


                } else {
                    System.out.println("Cache");
                    iv5.setImageBitmap(s.getBitmap(name));
                    s.replaceImage(5, name);
                }

            }
        });
        iv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MainActivity", "onClick: ");
                String name = s.getRandomImage();
                if (!s.inCache(name)) {
                    System.out.println(name);
                    new LoadImageTask(iv6).execute(s.getURL(name), name);
                    s.replaceImage(6, name);


                } else {
                    System.out.println("Cache");
                    iv6.setImageBitmap(s.getBitmap(name));
                    s.replaceImage(6, name);
                }

            }
        });
    }
}

