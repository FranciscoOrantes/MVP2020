package com.example.mvp.Modelo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.mvp.Presentador.LoginPresentador;
import com.example.mvp.Vista.LoginVista;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class Login implements LoginPresentador {
LoginVista vistaLogin;
AsyncHttpClient client = new AsyncHttpClient();

String token;
    public Login(LoginVista vistaLogin){
        this.vistaLogin =vistaLogin;

    }

    @Override
    public void inicioDeSesion(String usuario, String password) {
        Conexion conexion = new Conexion();
        RequestParams params = new RequestParams();
        System.out.println(conexion.getRuta());
        params.put("username",usuario);
        params.put("password",password);
        client.post(conexion.getRuta() + "/login", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                try {
                    JSONObject json = new JSONObject(new String(responseBody));
                    token = json.getString("token");
                    vistaLogin.inicioSesion(token);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                vistaLogin.error();
            }
        });
    }
}
