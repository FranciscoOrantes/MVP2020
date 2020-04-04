package com.example.mvp.Vista;

public interface DetallesVista {
    void detalleArticulo(String nombre,String descripcion,String precio,String tipo);
    void registroOActualizacionExitosa();
    void error();

}
