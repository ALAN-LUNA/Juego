package com.example.juego;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class Menu extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference JUGADORES;

    TextView uid,correo,usuario,Menutxt,Plataformas;
    Button btn_jugar,btn_puntuaciones,btn_acerca,btn_cerrarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        firebaseDatabase = FirebaseDatabase.getInstance();
        JUGADORES = firebaseDatabase.getReference("JUGADORES");

        Plataformas = findViewById(R.id.Plataformas);
        uid = findViewById(R.id.uid);
        correo = findViewById(R.id.correo);
        usuario = findViewById(R.id.usuario);
        Menutxt = findViewById(R.id.Menutxt);

        btn_jugar = findViewById(R.id.btn_jugar);
        btn_puntuaciones = findViewById(R.id.btn_puntuaciones);
        btn_acerca = findViewById(R.id.btn_acerca);
        btn_cerrarSesion = findViewById(R.id.btn_cerrarSesion);

        btn_cerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CerrarSesion();
            }
        });

    }
    //Al iniciar el activity consulta la BD
    protected void onStart() {
        Consulta();
        super.onStart();
    }

    private void CerrarSesion() {
        auth.signOut();
        startActivity(new Intent(Menu.this,MainActivity.class));
        Toast.makeText(this, "HASTA PRONTO", Toast.LENGTH_SHORT).show();
    }

    private void Consulta(){
        /*De la bd jugadores vamos a ordenarlos por correo para comparar con el correo actual */
        Query query = JUGADORES.orderByChild("Correo").equalTo(user.getEmail());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                /*hacemos un recorrido para reconocer el correo*/
                for(DataSnapshot ds : dataSnapshot.getChildren()){

                    /*llamar los datos especificos del usuario*/
                    String plataformasString = ""+ds.child("Plataformas").getValue();
                    String uidString = ""+ds.child("Uid").getValue();
                    String correoString = ""+ds.child("Correo").getValue();
                    String usuarioString = ""+ds.child("Usuario").getValue();

                    /*set de los valores de la BD*/
                    Plataformas.setText(plataformasString);
                    uid.setText(uidString);
                    correo.setText(correoString);
                    usuario.setText(usuarioString);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}