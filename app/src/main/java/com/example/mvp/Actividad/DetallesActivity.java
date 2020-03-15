package com.example.mvp.Actividad;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.mvp.Modelo.Detalles;
import com.example.mvp.Presentador.DetallesPresentador;
import com.example.mvp.Presentador.ListaPresentador;
import com.example.mvp.R;
import com.example.mvp.Vista.DetallesVista;

public class DetallesActivity extends AppCompatActivity implements DetallesVista {
String token;
    DetallesPresentador detalles;
int id;
    TextView txtDescripcion;
    TextView txtLogo;
    TextView txtCreate;
    TextView txtIdDist;
    TextView tituloCreate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);
        txtDescripcion = findViewById(R.id.txtDetalles);
        txtLogo = findViewById(R.id.txtLogo);
        txtIdDist = findViewById(R.id.txtIdDist);
        txtCreate = findViewById(R.id.txtCreate);
        tituloCreate = findViewById(R.id.textView9);
        detalles = new Detalles(DetallesActivity.this);
        token= getIntent().getExtras().getString("token");
        id= getIntent().getExtras().getInt("posicion");
        detalles.metodoGetDetalles(token,id);
    }

    @Override
    public void detalleArticulo(String descripcion, String logo, int distribuidora, String create) {
        if(token.equals("75b998251508230f843d33e716c7d68f401a86ee")){
            txtCreate.setVisibility(View.INVISIBLE);
            tituloCreate.setVisibility(View.INVISIBLE);
            txtDescripcion.setText(descripcion);
            txtLogo.setText(logo);
            txtIdDist.setText(String.valueOf(distribuidora));
        }else{
            txtCreate.setVisibility(View.VISIBLE);
            txtDescripcion.setText(descripcion);
            txtLogo.setText(logo);
            txtIdDist.setText(String.valueOf(distribuidora));
            txtCreate.setText(create);
        }
    }
}
