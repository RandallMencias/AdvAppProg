package com.example.proyecto_5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Consulta extends AppCompatActivity {

    private Spinner filtros;
    private TextView limiteinferior;
    private TextView limitesuperior;
    private Spinner tiposDeGasto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_consulta);


        //enlazar con los elementos
        filtros = (Spinner) findViewById(R.id.filtros);
        limiteinferior = (TextView) findViewById(R.id.limiteinferior);
        limitesuperior = (TextView) findViewById(R.id.limitesuperior);




        List<String> filtrosList = new ArrayList<String>(Arrays.asList("Tipo", "Fecha", "Rango"));
        ArrayAdapter<String> adapterFiltros = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, filtrosList);
        adapterFiltros.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filtros.setAdapter(adapterFiltros);


        tiposDeGasto =(Spinner)findViewById(R.id.tipos_de_gasto);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Elementos.getInstance().getTiposGastos());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tiposDeGasto.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        filtros.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedValue = parent.getItemAtPosition(position).toString();


                //TIPO

                if(selectedValue.equals("Tipo")){
                    tiposDeGasto.setVisibility(View.VISIBLE);
                    limiteinferior.setVisibility(View.GONE);
                    limitesuperior.setVisibility(View.GONE);










                }


                //FECHA

                else if (selectedValue.equals("Fecha")) {
                    tiposDeGasto.setVisibility(View.GONE);
                    limiteinferior.setVisibility(View.GONE);
                    limitesuperior.setVisibility(View.GONE);
                }




                //RANGO

                else if (selectedValue.equals("Rango")){
                    tiposDeGasto.setVisibility(View.GONE);
                    limiteinferior.setVisibility(View.VISIBLE);
                    limitesuperior.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




    }
}