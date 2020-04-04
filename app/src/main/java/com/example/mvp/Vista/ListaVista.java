package com.example.mvp.Vista;

import java.util.ArrayList;

public interface ListaVista {
    void mostrarArticulos(ArrayList ids, ArrayList nombres, ArrayList precios);
    void eliminadoExito();
    void error();
}
