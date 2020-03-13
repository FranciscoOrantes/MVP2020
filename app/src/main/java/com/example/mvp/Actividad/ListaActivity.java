package com.example.mvp.Actividad;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mvp.R;

public class ListaActivity extends AppCompatActivity {
String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        token= getIntent().getExtras().getString("token");
    }
}
