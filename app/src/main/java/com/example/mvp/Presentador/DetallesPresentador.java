package com.example.mvp.Presentador;

import com.loopj.android.http.RequestParams;

public interface DetallesPresentador {
    void metodoGetDetalles(String token, int id);
    void updateArticulo(String token, int id, RequestParams parametros);
    void metodoAgregarArticulo(String token, RequestParams parametros);
}
