package com.example.proyecto_5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.*;

public class Ajustes extends AppCompatActivity {
    private Elementos elementos = Elementos.getInstance();
    private List<String> lista = new ArrayList<>(elementos.getmapGastos().keySet());
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);

        //enlazar elementos
        listView = findViewById(R.id.list);

        setListView();
    }

    public void  setListView(){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista);
        listView.setAdapter(adapter);
    }
}