package com.example.juego;

import androidx.annotation.NonNull;
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

public class Login extends AppCompatActivity {

    EditText correoLoginEt,passLoginEt;
    Button Entrar;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        correoLoginEt = findViewById(R.id.correoLoginEt);
        passLoginEt = findViewById(R.id.passLoginEt);
        Entrar = findViewById(R.id.Entrar);
        auth = FirebaseAuth.getInstance();

        Entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = correoLoginEt.getText().toString();
                String pass = passLoginEt.getText().toString();

                    //VALIDACION PARA CORREO
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){//si el correo cumple con el formato, @ es valido
                    correoLoginEt.setError("correo invalido");
                    correoLoginEt.setFocusable(true);//el correo es invalido
                    // VALIDACION PARA CONTRASEÑA
                }else if(pass.length()<6){//si la contraseña es menor que 6
                    passLoginEt.setError("la contraseña debe ser mayor a 6");
                    passLoginEt.setFocusable(true);
                }else{
                    LoginDeJugador(email,pass);
                }

            }
        });
    }

    private void LoginDeJugador(String email, String pass) {
        auth.signInWithEmailAndPassword(email,pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser user = auth.getCurrentUser();
                            startActivity(new Intent(Login.this,Menu.class));
                            assert user != null;//el correo no es nulo
                            Toast.makeText(Login.this, "Bienvenido!!"+user.getEmail(), Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                    //Si es que falla el logeo nos manda un mensaje
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Login.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}