package com.example.agenda_f;

import android.widget.EditText;

//Creamos una clase nodo con sus datos respectivos y con su constructor
public class Nodo {
    String fecha;
    String actividad;
    Nodo siguiente;

    Nodo(EditText etd1, EditText etd2){
        actividad=etd1.getText().toString();
        fecha=etd2.getText().toString();
        siguiente=null;
    }
}

