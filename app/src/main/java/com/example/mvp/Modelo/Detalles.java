package com.example.mvp.Modelo;

import com.example.mvp.Presentador.DetallesPresentador;
import com.example.mvp.Vista.DetallesVista;
import com.example.mvp.Vista.ListaVista;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class Detalles implements DetallesPresentador {
    DetallesVista vistaDetalles;
    AsyncHttpClient client = new AsyncHttpClient();
    String descripcion;
    String logo;
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
                    descripcion=json.getString("descripcion");
                    logo = json.getString("logo");
                    create = json.getString("create");
                    distribuidora=json.getInt("distribuidora");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                vistaDetalles.detalleArticulo(descripcion,logo,distribuidora,create);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
}
