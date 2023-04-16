package com.example.proyecto_5;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private String textoprecio,selectedValue;
    private Spinner tiposDeGasto;
    private Elementos elementos = Elementos.getInstance();
    private Button btnFecha, btnEliminar, btnAdd;
    private EditText txtFecha, txtprecio;

    private int dia, mes, anio;
    private Calendar c;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //llenar spinner
        tiposDeGasto = (Spinner) findViewById(R.id.tipos_de_gasto);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Elementos.getInstance().getTiposGastos());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tiposDeGasto.setAdapter(adapter);
        adapter.notifyDataSetChanged();





        //enlazar con los elementos
        btnFecha = (Button) findViewById(R.id.btnfecha);
        btnAdd = (Button) findViewById(R.id.btnAgregar);
        btnEliminar = (Button) findViewById(R.id.btnRemove);
        txtFecha = (EditText) findViewById(R.id.txtFecha);
        txtprecio = (EditText) findViewById(R.id.editTextNumber);


        //llenar valores
        txtprecio.setText("0");
        c = Calendar.getInstance();
        dia = c.get(Calendar.DAY_OF_MONTH);
        mes = c.get(Calendar.MONTH);
        anio = c.get(Calendar.YEAR);
        txtFecha.setText(anio + "/" + (mes + 1) + "/" + dia);




        //listener de botones
        btnFecha.setOnClickListener(this);
        btnEliminar.setOnClickListener(this);
        btnAdd.setOnClickListener(this);

        tiposDeGasto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
            if (txtprecio.getText().toString().equals("") || txtprecio.getText().toString().equals("0")) {
                Toast toast = Toast.makeText(this, "No se ha ingresado un valor", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP| Gravity.CENTER, 0, 0);
                toast.show();
                txtprecio.setText("");
            }
            else if (txtFecha.getText().toString().equals("")) {
                Toast toast = Toast.makeText(this, "No se ha ingresado una fecha", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP|Gravity.CENTER, 0, 0);
                toast.show();
            }
            else {

            elementos.agregarGasto(txtFecha.getText().toString(), Double.parseDouble(txtprecio.getText().toString()), selectedValue);
            }
        } else if (view == btnEliminar) {
            if (txtprecio.getText().toString().equals("")) {
                Toast toast = Toast.makeText(this, "No se ha ingresado un valor", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP| Gravity.CENTER, 0, 0);
                toast.show();
                txtprecio.setText("0");
            }
            else if (txtFecha.getText().toString().equals("")) {
                Toast toast = Toast.makeText(this, "No se ha ingresado una fecha", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP|Gravity.CENTER, 0, 0);
                toast.show();
            }
            else {
                elementos.agregarGasto(txtFecha.getText().toString(), Double.parseDouble("-"+txtprecio.getText().toString()), selectedValue);
            }

            for (String tipo : elementos.getmapGastos().keySet()) {
                Log.i("Aguebo", "tipo gasto: " + tipo);
                for(Gastos gasto :  elementos.getmapGastos().get(tipo)){
                    Log.i("Adentro" , gasto.getValor() + "");
                    Log.i("Adentro" , gasto.getFecha() + "");
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