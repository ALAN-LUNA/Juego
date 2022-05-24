package com.example.juego;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Juego extends AppCompatActivity {

    String UIDS,USUARIOS,PLATAFORMAS;
    TextView TvContador,TvUsuario,TvTiempo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);

        TvContador = findViewById(R.id.TvContador);
        TvUsuario = findViewById(R.id.TvUsuario);
        TvTiempo = findViewById(R.id.TvTiempo);

        Bundle intent = getIntent().getExtras();//recuperar los archivos enviados de otro activity

        UIDS = intent.getString("UID");
        USUARIOS = intent.getString("USUARIO");
        PLATAFORMAS = intent.getString("PLATAFORMA");

        TvUsuario.setText(USUARIOS);
        TvContador.setText(PLATAFORMAS);

    }
}