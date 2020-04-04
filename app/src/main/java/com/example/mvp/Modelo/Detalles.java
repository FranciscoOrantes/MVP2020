package com.example.mvp.Modelo;

import com.example.mvp.Presentador.DetallesPresentador;
import com.example.mvp.Vista.DetallesVista;
import com.example.mvp.Vista.ListaVista;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class Detalles implements DetallesPresentador {
    DetallesVista vistaDetalles;
    AsyncHttpClient client = new AsyncHttpClient();
    String descripcion;
    String nombre;
    String precio;
    String tipo;
    int distribuidora;
    String create;
public Detalles(DetallesVista vistaDetalles){
    this.vistaDetalles=vistaDetalles;
}

    @Override
    public void metodoGetDetalles(String token, int id) {
        Conexion conexion = new Conexion();
        client.addHeader("Authorization","Token"+" "+ token);
        client.get(conexion.getRuta()+"/producto/productosEmpresa/action/"+id, new AsyncHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {

                    JSONObject json = new JSONObject(new String(responseBody));
                    nombre = json.getString("nombreProducto");
                    tipo = json.getString("tipoProducto");
                    descripcion=json.getString("descripcion");
                    precio = json.getString("precio");
                    System.out.println("este es el nombre "+nombre);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                vistaDetalles.detalleArticulo(nombre,descripcion,precio,tipo);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    @Override
    public void updateArticulo(String token, int id, RequestParams parametros) {
        Conexion conexion = new Conexion();
        client.addHeader("Authorization","Token"+" "+ token);
        client.put(conexion.getRuta() + "/producto/productosEmpresa/action/" + id, parametros, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                vistaDetalles.registroOActualizacionExitosa();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                vistaDetalles.error();
            }
        });
    }

    @Override
    public void metodoAgregarArticulo(String token, RequestParams parametros) {
        Conexion conexion = new Conexion();
        client.addHeader("Authorization","Token"+" "+ token);
        client.post(conexion.getRuta() + "/producto/productosEmpresa/", parametros, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                vistaDetalles.registroOActualizacionExitosa();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    vistaDetalles.error();
            }
        });
    }
}
