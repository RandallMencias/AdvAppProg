package com.example.proyecto_6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;


import androidx.appcompat.app.AppCompatActivity;


public class List extends AppCompatActivity {//clase controladora del ActivityMain
    private singleton s = singleton.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //enlazar con los componentes
        ImageView iv = (ImageView) findViewById(R.id.imageView1);
        ImageView iv2 = (ImageView) findViewById(R.id.imageView2);
        ImageView iv3 = (ImageView) findViewById(R.id.imageView3);
        ImageView iv4 = (ImageView) findViewById(R.id.imageView4);
        ImageView iv5 = (ImageView) findViewById(R.id.imageView5);
        ImageView iv6 = (ImageView) findViewById(R.id.imageView6);

        //funciones para el cambio de imagen de cada casilla
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = s.getRandomImage();
                if (!s.inCache(name)) {
                    new LoadImageTask(iv).execute(s.getURL(name), name);
                    s.replaceImage(1, name);

                } else {
                    iv.setImageBitmap(s.getBitmap(name));
                    s.replaceImage(1, name);
                }
            }
        });
        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = s.getRandomImage();
                if (!s.inCache(name)) {
                    new LoadImageTask(iv2).execute(s.getURL(name), name);
                    s.replaceImage(2, name);


                } else {
                    iv2.setImageBitmap(s.getBitmap(name));
                    s.replaceImage(2, name);
                }

            }
        });
        iv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = s.getRandomImage();
                if (!s.inCache(name)) {
                    new LoadImageTask(iv3).execute(s.getURL(name), name);
                    s.replaceImage(3, name);


                } else {
                    iv3.setImageBitmap(s.getBitmap(name));
                    s.replaceImage(3, name);
                }

            }
        });
        iv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = s.getRandomImage();
                if (!s.inCache(name)) {
                    new LoadImageTask(iv4).execute(s.getURL(name), name);
                    s.replaceImage(4, name);


                } else {
                    iv4.setImageBitmap(s.getBitmap(name));
                    s.replaceImage(4, name);
                }

            }
        });
        iv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = s.getRandomImage();
                if (!s.inCache(name)) {
                    new LoadImageTask(iv5).execute(s.getURL(name), name);
                    s.replaceImage(5, name);


                } else {
                    iv5.setImageBitmap(s.getBitmap(name));
                    s.replaceImage(5, name);
                }

            }
        });
        iv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = s.getRandomImage();
                if (!s.inCache(name)) {
                    new LoadImageTask(iv6).execute(s.getURL(name), name);
                    s.replaceImage(6, name);


                } else {
                    iv6.setImageBitmap(s.getBitmap(name));
                    s.replaceImage(6, name);
                }

            }
        });
    }
    public void launchScreen(View view){
        //volver a la actividad de lista
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}