package com.example.agenda_f;

//Importamos todas las bibliotecas que usaremos en el proyecto
import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    //Se crean Los botones lo edittext y el textview
    Button bFecha,bAgregar,bMostrar,bRealizado;
    TextView Tview;
    EditText etd1,etd2;

    //Creamos variables para la fecha
    private int Anio,Mes,Dia;

    //Ceamos apuntadore para el nodo
    private Nodo inicio = null;
    private Nodo ultimo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Enlasamos los botones los edittext y textview con los nombres que le asignamos Anteriormente
        bFecha = (Button) findViewById(R.id.but1);
        etd2 = (EditText) findViewById(R.id.etd2);
        bFecha.setOnClickListener(this::Fecha);
        etd2.setOnClickListener(this::Fecha);
        bAgregar = (Button) findViewById(R.id.but2);
        etd1 = (EditText) findViewById(R.id.etd1);
        bMostrar=(Button) findViewById(R.id.but3);
        bRealizado=(Button) findViewById(R.id.but4);
        Tview=(TextView) findViewById(R.id.Tv);

        //Inicialisamos el boton de Activida Realizada
        bRealizado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Realizado();
            }
        });

        ////Inicialisamos el boton de Mostrar
        bMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Actividades pendientes", Toast.LENGTH_SHORT).show();
                actualizarSalida();
            }
        });

        //Inicialisamos el boton de Agregar
        bAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Creamos un nodo nuevo
                Toast.makeText(MainActivity.this, "Se agrego actividad", Toast.LENGTH_SHORT).show();
                Nodo nuevoNodo=new Nodo( etd1 ,etd2);
                //Si inicio esta vacio le asignamos un nuevo Nodo en caso contrario se agrega un nodo mas
                if (inicio == null) {
                    inicio = nuevoNodo;
                    ultimo = nuevoNodo;
                } else {

                    ultimo.siguiente = nuevoNodo;
                    ultimo = nuevoNodo;
                }
                //Indicamos que los edittext se vacien despues de agregar los valores
                etd1.setText("");
                etd2.setText("");
            }

        });
    }

    //Creamos una funcion para que imprima los datos que hemos agregado, se llama atraves del buton mostrar
    private void actualizarSalida() {
        StringBuilder resultado = new StringBuilder();
        Nodo imp = inicio;
        while (imp != null) {
            resultado.append(imp.fecha).append(" ");
            resultado.append(imp.actividad).append("\n");
            imp = imp.siguiente;
        }

        TextView outputTextView = findViewById(R.id.Tv);
        outputTextView.setText(resultado.toString());
    }

    //Creamos una funcion la cual nos dara la fecha y podremos escoger la fecha que queromos
    public void Fecha(View view){
        final Calendar cal = Calendar.getInstance();
        Dia=cal.get(Calendar.DAY_OF_MONTH);
        Mes=cal.get(Calendar.MONTH);
        Anio=cal.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                etd2.setText(dayOfMonth+"/"+month+"/"+year);
            }
        },Anio,Mes,Dia);
        datePickerDialog.show();
    }

    //Creamos la funcion Realizado para borrar los datos ingresados al nodo
    public void Realizado(){
        Toast.makeText(this, "Ya no hay actividades ha realizar", Toast.LENGTH_SHORT).show();
        Tview.setText("");
    }

}