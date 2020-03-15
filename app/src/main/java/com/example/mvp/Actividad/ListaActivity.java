package com.example.mvp.Actividad;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.mvp.Modelo.ListaAlumnos;
import com.example.mvp.Modelo.Login;
import com.example.mvp.Presentador.ListaPresentador;
import com.example.mvp.Presentador.LoginPresentador;
import com.example.mvp.R;
import com.example.mvp.Vista.ListaVista;
import com.loopj.android.http.AsyncHttpClient;

import java.util.ArrayList;

public class ListaActivity extends AppCompatActivity implements ListaVista {
    String token;
    ListaPresentador lista;
    private String usuario;
    private String[]columnasUsuario1 = {"id","nombre","Detalles"};
    private String[]columnasUsuario2 = {"id","nombre","precio","Detalles"};
    TableRow idTR;
    TableRow nombreProductoTR;
    TableRow precioTR;
    AsyncHttpClient client;
    TableLayout tabla;
    TableRow fila;
    TableRow fila2;
    ArrayList ids;
    ArrayList nombres;
    ArrayList precios;
    TextView columna;
    TextView txtNombre;
    TextView txtPrecio;
    TextView txtId;
    Button btnDetalles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        tabla = findViewById(R.id.lista);
        client = new AsyncHttpClient();


        fila = new TableRow(getApplicationContext());
        fila2 = new TableRow(getApplicationContext());



        idTR = new TableRow(getApplicationContext());

        nombreProductoTR = new TableRow(getApplicationContext());
        precioTR = new TableRow(getApplicationContext());
        lista = new ListaAlumnos(ListaActivity.this);
        token= getIntent().getExtras().getString("token");

        lista.metodoGetAlumnos(token);
        inicializarDatos(token);
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
    public void mostrarAlumnos(ArrayList ids, ArrayList nombres, ArrayList precios) {
        if(token.equals("75b998251508230f843d33e716c7d68f401a86ee")){
            for(int i = 0;i<ids.size();i++) {
                final int posicion = i;
                TableRow tableRow = new TableRow(getApplicationContext());
                tabla.addView(tableRow);
                for (int j = 0; j < columnasUsuario1.length; j++) {

                    txtId= new TextView(getApplicationContext());
                    txtNombre= new TextView(getApplicationContext());



                    txtNombre.setGravity(Gravity.CENTER_VERTICAL);
                    txtNombre.setPadding(15, 15, 15, 15);

                    switch (j) {
                        case 0:
                            txtId.setText(ids.get(i).toString());
                            System.out.println(ids.get(i));
                            tableRow.addView(txtId);
                            break;
                        case 1:
                            txtNombre.setText(nombres.get(i).toString());
                            System.out.println(nombres.get(i));
                            tableRow.addView(txtNombre);
                            break;
                        case 2:
                            btnDetalles = new Button(getApplicationContext());
                            btnDetalles.setText("Ver detalles");
                            btnDetalles.setGravity(Gravity.CENTER_VERTICAL);

                            btnDetalles.setCompoundDrawablesWithIntrinsicBounds(R.drawable.pen,0,0,0);
                            btnDetalles.setPadding(15, 15, 15, 15);
                            btnDetalles.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    System.out.println("holi");
                                    //detalles(Integer.parseInt(ids.get(posicion).toString()));
                                }
                            });
                            tableRow.addView(btnDetalles);

                            break;

                    }
                }

            }
        }else{
            for(int i = 0;i<ids.size();i++) {
                final int posicion = i;
                TableRow tableRow = new TableRow(getApplicationContext());
                tabla.addView(tableRow);
                for (int j = 0; j < columnasUsuario2.length; j++) {

                    txtId= new TextView(getApplicationContext());
                    txtNombre= new TextView(getApplicationContext());
                    txtPrecio= new TextView(getApplicationContext());



                    txtNombre.setGravity(Gravity.CENTER_VERTICAL);
                    txtNombre.setPadding(15, 15, 15, 15);
                    txtPrecio.setGravity(Gravity.CENTER_VERTICAL);
                    txtPrecio.setPadding(15, 15, 15, 15);
                    switch (j) {
                        case 0:
                            txtId.setText(ids.get(i).toString());
                            System.out.println(ids.get(i));
                            tableRow.addView(txtId);
                            break;
                        case 1:
                            txtNombre.setText(nombres.get(i).toString());
                            System.out.println(nombres.get(i));
                            tableRow.addView(txtNombre);
                            break;
                        case 2:
                            txtPrecio.setText(precios.get(i).toString());
                            System.out.println(precios.get(i));
                            tableRow.addView(txtPrecio);
                            break;
                        case 3:
                            btnDetalles = new Button(getApplicationContext());
                            btnDetalles.setText("Ver detalles");
                            btnDetalles.setGravity(Gravity.CENTER_VERTICAL);

                            btnDetalles.setCompoundDrawablesWithIntrinsicBounds(R.drawable.pen,0,0,0);
                            btnDetalles.setPadding(15, 15, 15, 15);
                            btnDetalles.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    System.out.println("holi");
                                    //detalles(Integer.parseInt(ids.get(posicion).toString()));
                                }
                            });
                            tableRow.addView(btnDetalles);

                            break;

                    }
                }

            }
        }
    }
}
