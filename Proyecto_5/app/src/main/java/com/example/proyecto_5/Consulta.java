package com.example.proyecto_5;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class Consulta extends AppCompatActivity {
    private int dia, mes, anio;
    private Calendar c;
    private Spinner filtros;
    private TextView limiteinferior;
    private TextView limitesuperior,fecha;
    private Spinner tiposDeGasto;
    private Button btnFecha;
    private Elementos elementos = Elementos.getInstance();
    private ListView display;
    private TextView total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        c = Calendar.getInstance();
        super.onCreate(savedInstanceState);
        String Date;

        setContentView(R.layout.activity_consulta);


        //enlazar con los elementos
        filtros = (Spinner) findViewById(R.id.filtros);
        tiposDeGasto = (Spinner) findViewById(R.id.tipos_de_gasto);
        limiteinferior = (TextView) findViewById(R.id.limiteinferior);
        limitesuperior = (TextView) findViewById(R.id.limitesuperior);
        fecha = (TextView)findViewById(R.id.textViewDate);
        btnFecha = (Button)findViewById(R.id.btnDate);
        display = (ListView)findViewById(R.id.listview);
        total = (TextView)findViewById(R.id.totaldegastos);


        limiteinferior.setText("0");
        limitesuperior.setText("0");




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
                    btnFecha.setVisibility(View.GONE);
                    fecha.setVisibility(View.GONE);


                    tiposDeGasto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            // This code will be executed when an item in the spinner is selected
                            String selectedValue = parent.getItemAtPosition(position).toString();
                            ArrayAdapter<String> adapter = new ArrayAdapter<>(Consulta.this,
                                    android.R.layout.simple_list_item_1, elementos.getGastosPorTipo(tiposDeGasto.getSelectedItem().toString()));
                            display.setAdapter(adapter);
                            total.setText("Total de gastos: "+elementos.getGastoTotalTipo(tiposDeGasto.getSelectedItem().toString()));
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                            // This code will be executed when the spinner is initialized
                            // and nothing is selected
                        }
                    });



                }


                //FECHA

                else if (selectedValue.equals("Fecha")) {
                    tiposDeGasto.setVisibility(View.GONE);
                    limiteinferior.setVisibility(View.GONE);
                    limitesuperior.setVisibility(View.GONE);
                    btnFecha.setVisibility(View.VISIBLE);
                    fecha.setVisibility(View.VISIBLE);
                    dia = c.get(Calendar.DAY_OF_MONTH);
                    mes = c.get(Calendar.MONTH);
                    anio = c.get(Calendar.YEAR);


                    btnFecha.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            DatePickerDialog datePickerDialog = new DatePickerDialog(Consulta.this, new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int month, int day) {
                                    fecha.setText(year + "/" + (month + 1) + "/" + day);
                                    ArrayAdapter<String> adapter = new ArrayAdapter<>(Consulta.this,
                                            android.R.layout.simple_list_item_1, elementos.getGastosPorFecha(fecha.getText().toString()));
                                    display.setAdapter(adapter);
                                    total.setText("Total de gastos: "+elementos.getGastoTotalFecha(fecha.getText().toString()));
                                }
                            }, anio, mes, dia);
                            datePickerDialog.show();

                        }

                    });



                }



                //RANGO

                else if (selectedValue.equals("Rango")){


                    tiposDeGasto.setVisibility(View.GONE);
                    limiteinferior.setVisibility(View.VISIBLE);
                    limitesuperior.setVisibility(View.VISIBLE);
                    btnFecha.setVisibility(View.GONE);
                    fecha.setVisibility(View.GONE);

                    limitesuperior.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            if(!limitesuperior.getText().toString().equals("") && !limiteinferior.getText().toString().equals("")){
                                ArrayAdapter<String> adapter = new ArrayAdapter<>(Consulta.this,
                                        android.R.layout.simple_list_item_1, elementos.getGastosPorRango(
                                                Double.parseDouble(limiteinferior.getText().toString()),Double.parseDouble(limitesuperior.getText().toString())));
                                display.setAdapter(adapter);
                                total.setText("Total de gastos: "+elementos.getGastoTotalRango(
                                        Double.parseDouble(limiteinferior.getText().toString()),Double.parseDouble(limitesuperior.getText().toString())));

                            }
                        }
                    });






                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




    }

}