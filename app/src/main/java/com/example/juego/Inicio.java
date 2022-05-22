package com.example.juego;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Inicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        int duracionInicio = 1500;
        //Handler es un tipo de objeto, ejecuta lineas de codigo en un tiempo determinado
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //codigo que se ejecuta
                startActivity(new Intent(Inicio.this,MainActivity.class));
            }
        }, duracionInicio);
        //tiempo de hadnler
    }
}