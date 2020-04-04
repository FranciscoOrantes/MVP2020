package com.example.mvp.Presentador;

import com.loopj.android.http.RequestParams;

public interface ListaPresentador {
    void metodoGetArticulos(String token);
    void metodoEliminarArticulo(String token, int id);

}
