package com.example.proyecto_5;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private String textoprecio;
    private Elementos elementos =Elementos.getInstance();
    Button btnFecha,btnEliminar,btnAdd;
    EditText txtFecha,txtprecio,temp;

    private int dia, mes, anio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //enlazar con los elementos
        btnFecha = (Button)findViewById(R.id.btnfecha);
        btnAdd = (Button)findViewById(R.id.btnAgregar) ;
        btnEliminar = (Button)findViewById(R.id.btnRemove);
        txtFecha = (EditText)findViewById(R.id.txtFecha);
        txtprecio = (EditText)findViewById(R.id.editTextNumber);

        temp = (EditText)findViewById(R.id.temptxt);

        //listener de botones
        btnFecha.setOnClickListener(this);
        btnEliminar.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
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
                    txtFecha.setText(year + "/" + (month+1) + "/" + day);
                }
            },anio, mes, dia);
            datePickerDialog.show();
        } else if (view == btnAdd) {
            elementos.agregarGasto(txtFecha.getText().toString(),Double.parseDouble(txtprecio.getText().toString()),temp.getText().toString());
        }else if(view == btnEliminar){
            Log.i("Aguebo","A");
            for (String tipo : elementos.getmapGastos().keySet()) {
                Log.i("Aguebo","tipo gasto: " + tipo);
//                List<Gastos> gastos = elementos.getGastos().get(tipo);
//                for(Gastos gasto : gastos);
                /*Gastos gasto = elementos.getGastos().get(tipo);
                Log.i("Aguebo","tipo gasto: " + tipo);
                Log.i("Aguebo","fecha del gasto: " + gasto.getFecha());
                Log.i("Aguebo","precio del gasto: " + gasto.getValor());*/

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