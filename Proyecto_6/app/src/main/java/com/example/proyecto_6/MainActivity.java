//Juan Diego Venegas Barreto 00209856
//Randall Mencias 00321469
//clase: Programacion Avanzada de Apps
//Proyecto 6. Decarga de Imágenes en Paralelo
package com.example.proyecto_6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {//clase controladora de ActivityList
    private ListView lvNasa;
    private singleton s = singleton.getInstance(); //instancia de la clase singleton

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //conectar elementos
        setContentView(R.layout.activity_list);
        lvNasa = findViewById(R.id.lvnasa);
        //adapter para LV
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<String>());
        lvNasa.setAdapter(adapter);
        if(s.showImageMap().isEmpty()){
            adapter.add("Inicializando...");
        }

        LoadData task = new LoadData("https://nasasearch.nasa.gov/search?affiliate=nasa&page=6&query=%2A.jpg&sort_by=&utf8=%E2%9C%93", adapter);
        task.execute();
        //mantener elementos en el LV
        if(adapter.isEmpty()){
            adapter.add(s.showImageMap().toString());
        }


    }
    public void launchMain(View view){
        //ir a activityMain
        Intent intent = new Intent(this, List.class);
        startActivity(intent);
    }
}

