package com.example.proyecto_6;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import androidx.appcompat.app.AppCompatActivity;


public class List extends AppCompatActivity {
    private singleton s = singleton.getInstance();
    private ListView lvNasa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        lvNasa = findViewById(R.id.lvnasa);

        /*ArrayAdapter<String> adapter = (ArrayAdapter<String>) lvNasa.getAdapter();
        adapter.add(s.showImageMap().toString());
        adapter.notifyDataSetChanged();*/
    }


}