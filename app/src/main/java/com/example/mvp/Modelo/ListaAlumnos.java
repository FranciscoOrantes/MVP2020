package com.example.mvp.Modelo;

import com.example.mvp.Presentador.ListaPresentador;
import com.example.mvp.Vista.ListaVista;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class ListaAlumnos implements ListaPresentador {
    ListaVista vistaLista;
    AsyncHttpClient client = new AsyncHttpClient();
    String ruta = "http://85343b4e.ngrok.io";
    String token;

    @Override
    public void metodoGetAlumnos(String token) {
        client.addHeader("Authorization","Token"+" "+ token);
        client.get(ruta+"/producto/productosEmpresa/", new AsyncHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
    public ListaAlumnos(ListaVista vistaLista){
        this.vistaLista =vistaLista;

    }
}
