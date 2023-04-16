package com.example.proyecto_5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Gravity;
import android.widget.*;
import android.os.Bundle;
import android.view.View;


import java.util.*;

public class Ajustes extends AppCompatActivity {
    private Elementos elementos = Elementos.getInstance();
    private List<String> lista;
    private ListView listView;
    private TextView txtMax;
    private EditText nuevoTipo,nuevoValor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);


        //enlazar elementos
        listView = findViewById(R.id.list);
        txtMax = findViewById(R.id.gastos_max);
        nuevoTipo = findViewById(R.id.newType);
        nuevoValor = findViewById(R.id.newTypeValue);

        setListView();
        //Configurar listener para el ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String iosKey = (String) parent.getItemAtPosition(position);

                // Crear el layout personalizado para el AlertDialog
                View dialogView = getLayoutInflater().inflate(R.layout.dialog_layout, null);

                // Obtener las referencias de los elementos de la vista
                TextView textView1 = dialogView.findViewById(R.id.MaxValuetxt);
                EditText newVal = dialogView.findViewById(R.id.NewMaxValuetxt);
                Button btnModificarMax = dialogView.findViewById(R.id.btnModify);

                // Configurar los elementos con la información deseada
                textView1.setText(elementos.getmapGastosMax().get(iosKey).toString());
                btnModificarMax.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){

                        elementos.setmapGastosMax(iosKey,Double.parseDouble(newVal.getText().toString()));
                        gastosMax();
                    }
                });
                // Crear el AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(Ajustes.this);
                builder.setView(dialogView);
                builder.setPositiveButton("Aceptar", null);
                builder.show();
            }
        });
        gastosMax();
    }

    public void  setListView(){
        lista = new ArrayList<>(elementos.getmapGastos().keySet());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista);
        listView.setAdapter(adapter);
    }
    public void gastosMax(){
        double num = 0;
        for(String str : elementos.getmapGastosMax().keySet()){
            num = num + elementos.getmapGastosMax().get(str);
        }
        txtMax.setText("Gasto Maximo: " + num);
    }

    public void nuevoTipoGasto(View view){
        try {
            if(nuevoTipo.getText().toString().equals("") || nuevoValor.getText().toString().equals("") || elementos.getmapGastos().containsKey(nuevoTipo.getText().toString())){
                Toast toast = Toast.makeText(this, "Error en la seleccion", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP| Gravity.CENTER, 0, 0);
                toast.show();
            }else{
                elementos.nuevoTipoGasto(nuevoTipo.getText().toString(),Double.parseDouble(nuevoValor.getText().toString()));
                setListView();

                nuevoTipo.getText().toString();
            }
        }catch (Exception e){
            Toast toast = Toast.makeText(this, "El numero ingresado no es valido", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP| Gravity.CENTER, 0, 0);
            toast.show();
        }
    }
}