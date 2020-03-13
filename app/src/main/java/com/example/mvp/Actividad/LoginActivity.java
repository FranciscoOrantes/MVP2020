package com.example.mvp.Actividad;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mvp.Modelo.Login;
import com.example.mvp.Presentador.LoginPresentador;
import com.example.mvp.R;
import com.example.mvp.Vista.LoginVista;

public class LoginActivity extends AppCompatActivity implements LoginVista {
LoginPresentador login;
EditText txtUsuario;
EditText txtPassword;
Button btnIniciar;
String usuario;
String password;
private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = new Login(LoginActivity.this);
        btnIniciar= findViewById(R.id.btnIniciarSesion);
        txtUsuario = findViewById(R.id.txtUsuario);
        txtPassword  = findViewById(R.id.txtPassword);

        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usuario = txtUsuario.getText().toString();
                password = txtPassword.getText().toString();
                progressDialog = ProgressDialog.show(LoginActivity.this, "Iniciando Sesión", "Por favor espere", true, true);
                login.inicioDeSesion(usuario,password);
            }
        });
    }


    @Override
    public void inicioSesion(String token) {
        progressDialog.dismiss();
        Intent intent = new Intent(LoginActivity.this, ListaActivity.class);
        intent.putExtra("token",token);
        Toast.makeText(getApplicationContext(),"Inicio de sesion exitoso",Toast.LENGTH_LONG).show();
        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }

    @Override
    public void error() {
        progressDialog.dismiss();
        Toast.makeText(getApplicationContext(),"Ha ocurrido un error",Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onResume() {
        super.onResume();
        txtUsuario.setText(" ");
        txtPassword.setText(" ");
    }
}
