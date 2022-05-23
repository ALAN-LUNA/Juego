package com.example.juego;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class Menu extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;

    TextView Puntuaciontxt,uid,correo,usuario,Menutxt;
    Button btn_jugar,btn_puntuaciones,btn_acerca,btn_cerrarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Puntuaciontxt = findViewById(R.id.Puntuaciontxt);
        uid = findViewById(R.id.uid);
        correo = findViewById(R.id.correo);
        usuario = findViewById(R.id.usuario);
        Menutxt = findViewById(R.id.Menutxt);

        btn_jugar = findViewById(R.id.btn_jugar);
        btn_puntuaciones = findViewById(R.id.btn_puntuaciones);
        btn_acerca = findViewById(R.id.btn_acerca);
        btn_cerrarSesion = findViewById(R.id.btn_cerrarSesion);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        FirebaseDatabase firebaseDatabase;
        DatabaseReference JUGADORES;

        btn_jugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btn_puntuaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btn_acerca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btn_cerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CerrarSesion();
            }
        });

    }

    private void CerrarSesion() {
        auth.signOut();
        startActivity(new Intent(Menu.this,MainActivity.class));
        Toast.makeText(this, "HASTA PRONTO", Toast.LENGTH_SHORT).show();
    }
}