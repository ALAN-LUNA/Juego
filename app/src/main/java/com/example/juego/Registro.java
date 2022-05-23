package com.example.juego;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.regex.Pattern;

public class Registro extends AppCompatActivity {
    //declarado las variables
    EditText correoEt,passEt,usuarioEt;
    Button Registrar;
    FirebaseAuth auth; //Gestionar usuarios

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        correoEt = findViewById(R.id.correoEt);
        passEt = findViewById(R.id.passEt);
        usuarioEt = findViewById(R.id.usuarioEt);
        Registrar = findViewById(R.id.Registrar);

        auth = FirebaseAuth.getInstance();

        Registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = correoEt.getText().toString();//encadena los datos
                String password = passEt.getText().toString();

                    //VALIDACION PARA CORREO
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){//si el correo cumple con el formato, @ es valido
                    correoEt.setError("correo invalido");
                    correoEt.setFocusable(true);//el correo es invalido
                    // VALIDACION PARA CONTRASEÑA
                }else if(password.length()<6){//si la contraseña es menor que 6
                    passEt.setError("la contraseña debe ser mayor a 6");
                    passEt.setFocusable(true);
                }else{
                    RegistrarJugador(email,password);
                }
            }
        });
    }
    /*registra un jugador*/
    private void RegistrarJugador(String email, String password) {
        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        /*si el jugador fue registrado correctamente*/
                        if(task.isSuccessful()) {
                            FirebaseUser user = auth.getCurrentUser();

                            int contador = 0;

                            assert user != null; //VERIFICA QUE EL USUARIO NO ES NULO

                            /*Strings para indentificar lo que escribe*/
                            String uidString = user.getUid();//UID ES IMPORTANTE ES UN ID DE USUARIO EN LA BASE DE DATOS
                            String correoString = correoEt.getText().toString();
                            String passString = passEt.getText().toString();
                            String usuarioString = usuarioEt.getText().toString();

                            //asigna claves a los valores
                            HashMap<Object, Object> DatosJugador = new HashMap<>();

                            DatosJugador.put("Uid", uidString);
                            DatosJugador.put("Correo", correoString);
                            DatosJugador.put("Password", passString);
                            DatosJugador.put("Usuario", usuarioString);
                            DatosJugador.put("Plataformas", contador);

                            FirebaseDatabase database = FirebaseDatabase.getInstance();//instancia
                            DatabaseReference reference = database.getReference("JUGADORES");//referencia al nombre de la base de datos
                            reference.child(uidString).setValue(DatosJugador);
                            startActivity(new Intent(Registro.this,Menu.class));
                            Toast.makeText(Registro.this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(Registro.this, "Error al registrarse", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                /*si falla el registro manda un mensaje de que esta fallando*/
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Registro.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}