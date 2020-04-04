package com.example.mvp.Actividad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.mvp.Modelo.ListaArticulos;
import com.example.mvp.Presentador.ListaPresentador;
import com.example.mvp.R;
import com.example.mvp.Vista.ListaVista;
import com.loopj.android.http.AsyncHttpClient;

import java.util.ArrayList;

public class ListaActivity extends AppCompatActivity implements ListaVista {
    String token;
    ListaPresentador lista;
    private String[]columnasUsuario1 = {"id","nombre","Detalles"};
    private String[]columnasUsuario2 = {"id","nombre","precio","Detalles"};
    TableRow idTR;
    TableRow nombreProductoTR;
    TableRow precioTR;
    AsyncHttpClient client;
    TableLayout tabla;
    TableRow fila;
    TableRow fila2;
    TextView columna;
    ImageView btnRegistrarArt;
    ArrayAdapter  adapter;
    ListView listaArticulos;
    String titulo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        listaArticulos = findViewById(R.id.lista);
        client = new AsyncHttpClient();
        btnRegistrarArt = findViewById(R.id.btnArt);







        lista = new ListaArticulos(ListaActivity.this);
        token= getIntent().getExtras().getString("token");

        lista.metodoGetArticulos(token);
        btnRegistrarArt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                titulo = "Registrar";
                vistaRegistro(titulo);
            }
        });
        //inicializarDatos(token);
    }
    public void inicializarDatos(String token){
        if(token.equals("75b998251508230f843d33e716c7d68f401a86ee")){
            System.out.println("HA ENTRADO AL IF DE THEMAXIMUSFRAN");
            for (int i = 0; i< columnasUsuario1.length;i++){
                columna = new TextView(getApplicationContext());
                columna.setGravity(Gravity.CENTER_VERTICAL);
                columna.setPadding(15,15,15,15);
                columna.setText(columnasUsuario1[i]);
                fila.addView(columna);

            }
            tabla.addView(fila);
           // recuperarDatos(token);
        }else{
            for (int i = 0; i< columnasUsuario2.length;i++){
                columna = new TextView(getApplicationContext());
                columna.setGravity(Gravity.CENTER_VERTICAL);
                columna.setPadding(15,15,15,15);
                columna.setText(columnasUsuario2[i]);
                fila.addView(columna);

            }
            tabla.addView(fila);
           // recuperarDatos(token);
        }
    }

    @Override
    public void mostrarArticulos(final ArrayList ids, ArrayList nombres, ArrayList precios) {
        adapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,nombres);
        listaArticulos.setAdapter( adapter);
        listaArticulos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                titulo = "Actualizar";
                detalles((Integer) ids.get(position),titulo);

            }
        });
    }
    public void detalles(int posicion,String titulo){
        Bundle bundle = new Bundle();
        Intent intent = new Intent(ListaActivity.this,DetallesActivity.class);
        bundle.putString("token", token);
        bundle.putString("titulo", titulo);
        bundle.putInt("posicion", posicion);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    public void vistaRegistro(String titulo){
        Bundle bundle = new Bundle();
        Intent intent = new Intent(ListaActivity.this,DetallesActivity.class);
        bundle.putString("token", token);
        bundle.putString("titulo", titulo);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}
