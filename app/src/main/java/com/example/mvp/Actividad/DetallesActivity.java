package com.example.mvp.Actividad;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvp.Modelo.Detalles;
import com.example.mvp.Presentador.DetallesPresentador;
import com.example.mvp.Presentador.ListaPresentador;
import com.example.mvp.R;
import com.example.mvp.Vista.DetallesVista;
import com.loopj.android.http.RequestParams;

public class DetallesActivity extends AppCompatActivity implements DetallesVista {
    String token,titulo;
    DetallesPresentador detalles;
    int id;
    TextView txtDescripcion;
    TextView txtNombre;
    TextView txtPrecio;
    TextView txtTipo;
    TextView txtTitulo;
    Button btnActualizar;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);
        txtDescripcion = findViewById(R.id.txtDescripcion);
        txtNombre = findViewById(R.id.txtNombre);
        txtPrecio = findViewById(R.id.txtPrecio);
        btnActualizar = findViewById(R.id.btnRegistrar);
        txtTitulo = findViewById(R.id.txtTitulo);
        txtTipo = findViewById(R.id.txtTipo);
        detalles = new Detalles(DetallesActivity.this);
        token= getIntent().getExtras().getString("token");
        titulo= getIntent().getExtras().getString("titulo");

        if(titulo.equals("Actualizar")){
            id= getIntent().getExtras().getInt("posicion");
            detalles.metodoGetDetalles(token,id);
            txtTitulo.setText(titulo + " Producto");
            btnActualizar.setText(titulo);
        }else{
            txtTitulo.setText(titulo + " Producto");
            btnActualizar.setText(titulo);
        }
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txtProgress = null;
                RequestParams params = new RequestParams();
                params.put("nombreProducto",txtNombre.getText().toString());
                params.put("distribuidora",0);
                params.put("descripcion",txtDescripcion.getText().toString());
                params.put("tipoProducto",txtTipo.getText().toString());
                params.put("precio",txtPrecio.getText().toString());
                params.put("logo","https://facebook.com");
                if (btnActualizar.getText().equals("Actualizar")){
                    txtProgress="Actualizando";

                    progressDialog = ProgressDialog.show(DetallesActivity.this, txtProgress, "Por favor espere", true, true);
                    detalles.updateArticulo(token,id,params);
                }else{

                    detalles.metodoAgregarArticulo(token,params);

                }
            }
        });

    }

    @Override
    public void detalleArticulo(String nombre,String descripcion, String precio,String tipo) {
            txtNombre.setText(nombre);
            txtDescripcion.setText(descripcion);
            txtPrecio.setText(precio);
            txtTipo.setText(tipo);
        }

    @Override
    public void registroOActualizacionExitosa() {
        progressDialog.dismiss();
        if(titulo.equals("Actualizar")){
            Toast.makeText(getApplicationContext(),"Se ha actualizado con éxito",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(),"Se ha registrado con éxito",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void error() {
        progressDialog.dismiss();
        Toast.makeText(getApplicationContext(),"Ha ocurrido un error",Toast.LENGTH_LONG).show();
    }
}
