package com.example.juego;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class Juego extends AppCompatActivity {

    String UIDS,USUARIOS,PLATAFORMAS;
    TextView TvContador,TvUsuario,TvTiempo;
    ImageView IvPlataforma;

    TextView AnchoTv,AltoTv;

    int anchoPantalla;
    int altoPantalla;

    Random aleatorio;

    int contador = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);

        TvContador = findViewById(R.id.TvContador);
        TvUsuario = findViewById(R.id.TvUsuario);
        TvTiempo = findViewById(R.id.TvTiempo);
        IvPlataforma = findViewById(R.id.IvPlataforma);
        AnchoTv = findViewById(R.id.AnchoTv);
        AltoTv = findViewById(R.id.AltoTv);

        Bundle intent = getIntent().getExtras();//recuperar los archivos enviados de otro activity

        UIDS = intent.getString("UID");
        USUARIOS = intent.getString("USUARIO");
        PLATAFORMAS = intent.getString("PLATAFORMA");

        TvUsuario.setText(USUARIOS);
        TvContador.setText(PLATAFORMAS);

        pantalla();

        IvPlataforma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*al dar click en la imagen sube el contador lo setea al text view*/
                contador++;
                TvContador.setText(String.valueOf(contador));//convertir a string para el tv

                IvPlataforma.setImageResource(R.drawable.plataformapulsada);

                /*permite volver la imagen al estado original */
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        IvPlataforma.setImageResource(R.drawable.plataforma);
                        movimiento();//llama al metodo para generar movimiento
                    }
                }, 500);

            }
        });
    }
    /*obtener el tamaño de la pantalla*/
    private void pantalla(){

        Display display = getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);

        anchoPantalla = point.x;
        altoPantalla = point.y;

        String ANCHOS = String.valueOf(anchoPantalla);
        String ALTOS = String.valueOf(altoPantalla);

        AnchoTv.setText(ANCHOS);
        AltoTv.setText(ALTOS);

        aleatorio = new Random();

    }

    private void movimiento(){

        int min = 0;//inicializar el minimo de x y

        int maximoX = anchoPantalla - IvPlataforma.getWidth();//lo maximo que la imagen puede moverse en x
        int maximoY = altoPantalla - IvPlataforma.getHeight();//hasta donde llega y
        /*FORMULA PARA DAR MOVIMIENTO A LA PLATAFORMA*/
        int randomX = aleatorio.nextInt(((maximoX - min) + 1)+min);
        int randomY = aleatorio.nextInt(((maximoY - min) + 1)+min);

        IvPlataforma.setX(randomX);
        IvPlataforma.setY(randomY);

    }
}