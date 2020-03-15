package com.example.mvp.Modelo;

import com.example.mvp.Presentador.ListaPresentador;
import com.example.mvp.Vista.ListaVista;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class ListaArticulos implements ListaPresentador {
    ListaVista vistaLista;
    AsyncHttpClient client = new AsyncHttpClient();
    private ArrayList nombres = new ArrayList();
    private ArrayList ids = new ArrayList();
    private ArrayList precios = new ArrayList();
    @Override
    public void metodoGetArticulos(String token) {
        Conexion conexion = new Conexion();
        client.addHeader("Authorization","Token"+" "+ token);
        client.get(conexion.getRuta()+"/producto/productosEmpresa/", new AsyncHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    ids.clear();
                    nombres.clear();
                    precios.clear();
                    JSONArray array = new JSONArray(new String(responseBody));
                    JSONObject json;
                    for (int i = 0; i< array.length();i++) {
                        json = array.getJSONObject(i);
                        ids.add(json.getInt("id"));
                        nombres.add(json.getString("nombreProducto"));
                        precios.add(json.getString("precio"));

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                vistaLista.mostrarArticulos(ids,nombres,precios);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
    public ListaArticulos(ListaVista vistaLista){
        this.vistaLista =vistaLista;

    }


}
