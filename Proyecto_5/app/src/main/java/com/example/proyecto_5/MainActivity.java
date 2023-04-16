package com.example.proyecto_5;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private String textoprecio,selectedValue;
    private Spinner spinner;
    private Elementos elementos = Elementos.getInstance();
    private Button btnFecha, btnEliminar, btnAdd;
    private EditText txtFecha, txtprecio, temp;

    private int dia, mes, anio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //llenar spinner
        spinner = (Spinner) findViewById(R.id.tipos_de_gasto);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Elementos.getInstance().getTiposGastos());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        //enlazar con los elementos
        btnFecha = (Button) findViewById(R.id.btnfecha);
        btnAdd = (Button) findViewById(R.id.btnAgregar);
        btnEliminar = (Button) findViewById(R.id.btnRemove);
        txtFecha = (EditText) findViewById(R.id.txtFecha);
        txtprecio = (EditText) findViewById(R.id.editTextNumber);


        //listener de botones
        btnFecha.setOnClickListener(this);
        btnEliminar.setOnClickListener(this);
        btnAdd.setOnClickListener(this);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // This code will be executed when an item in the spinner is selected
                selectedValue = parent.getItemAtPosition(position).toString();
                Log.i("Aguebo", "Valor seleccionado: " + selectedValue);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // This code will be executed when the spinner is initialized
                // and nothing is selected
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view == btnFecha) {
            Calendar c = Calendar.getInstance();
            dia = c.get(Calendar.DAY_OF_MONTH);
            mes = c.get(Calendar.MONTH);
            anio = c.get(Calendar.YEAR);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int day) {
                    txtFecha.setText(year + "/" + (month + 1) + "/" + day);
                }
            }, anio, mes, dia);
            datePickerDialog.show();
        } else if (view == btnAdd) {
            elementos.agregarGasto(txtFecha.getText().toString(), Double.parseDouble(txtprecio.getText().toString()), selectedValue);
        } else if (view == btnEliminar) {
            Log.i("Aguebo", "A");
            for (String tipo : elementos.getmapGastos().keySet()) {
                Log.i("Aguebo", "tipo gasto: " + tipo);
                for(Gastos gasto :  elementos.getmapGastos().get(tipo)){
                    Log.i("Adentro" , gasto.getValor() + "");
                }

            }
        }

    }

    public void launchConsulta(View view) {
        Intent intent = new Intent(this, Consulta.class);
        startActivity(intent);
    }

    public void launchAjustes(View view) {
        Intent intent = new Intent(this, Ajustes.class);
        startActivity(intent);
    }


}