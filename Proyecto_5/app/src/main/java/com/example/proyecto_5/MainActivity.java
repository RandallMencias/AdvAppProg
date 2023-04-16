package com.example.proyecto_5;
//Juan Diego Venegas Barreto 00209856
//Randall Mencias 00321469
//clase: Programacion Avanzada de Apps
//Proyecto 5. Anexo de Gastos Personales
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
    private String selectedValue;//Guardar valor der spinner
    private Spinner tiposDeGasto;
    private Elementos elementos = Elementos.getInstance(); //Instancia de clase Elementos
    private Button btnFecha, btnEliminar, btnAdd;
    private EditText txtFecha, txtprecio, txtItem;
    //Elementos para picker de calendario
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
        txtItem = (EditText) findViewById(R.id.editTextItem);

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
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // This code will be executed when the spinner is initialized
                // and nothing is selected
            }
        });
    }
    //funcion para los eventos
    @Override
    public void onClick(View view) {
        if (view == btnFecha) {//usa boton para mostrar fecha
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
        } else if (view == btnAdd) {//usa boton para agregar
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
            else if (Double.parseDouble(txtprecio.getText().toString())>elementos.getmapGastosMax().get(selectedValue)){
                Toast toast = Toast.makeText(this, "El numero ingresado supera el valor maximo permitido", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP|Gravity.CENTER, 0, 0);
                toast.show();
            }
            else if(txtItem.getText().toString().equals("")){
                Toast toast = Toast.makeText(this, "No se ha ingresado item", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP|Gravity.CENTER, 0, 0);
                toast.show();
            }
            else{
                elementos.agregarGasto(txtFecha.getText().toString(), Double.parseDouble(txtprecio.getText().toString()), selectedValue, txtItem.getText().toString());
                txtprecio.setText("");
                txtItem.setText("");
                Toast toast = Toast.makeText(this, "Gasto agregado con exito", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP|Gravity.CENTER, 0, 0);
                toast.show();
            }
        } else if (view == btnEliminar) { //boton para eliminar
           if(txtItem.getText().toString().equals("")){
                Toast toast = Toast.makeText(this, "No se ha ingresado item", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP|Gravity.CENTER, 0, 0);
                toast.show();
            } else{
               try {
                   elementos.eliminarGasto(selectedValue, txtItem.getText().toString());
                   txtprecio.setText("");
                   txtItem.setText("");
                   Toast toast = Toast.makeText(this, "Item eliminado con exito", Toast.LENGTH_SHORT);
                   toast.setGravity(Gravity.TOP|Gravity.CENTER, 0, 0);
                   toast.show();
               } catch(Exception e){
                   Toast toast = Toast.makeText(this, "El elemento a eliminar no existe", Toast.LENGTH_SHORT);
                   toast.setGravity(Gravity.TOP|Gravity.CENTER, 0, 0);
                   toast.show();
               }
           }

        }
    }
    //funcion para cambiar a pantalla de consulta
    public void launchConsulta(View view) {
        Intent intent = new Intent(this, Consulta.class);
        startActivity(intent);
    }
    //funcion para cambiar a pantalla editar
    public void launchAjustes(View view) {
        Intent intent = new Intent(this, Ajustes.class);
        startActivity(intent);
    }



}