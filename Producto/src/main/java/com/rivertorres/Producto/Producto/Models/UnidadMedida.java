package com.rivertorres.Producto.Producto.Models;

import lombok.Data;
import lombok.NoArgsConstructor;



public enum UnidadMedida {

    PIEZA(1, "Pieza", "PZA"),
    KILOGRAMO(2, "Kilogramo", "KG"),
    LITRO(3, "Litro", "LT");

    private final int id;
    private final String nombre;
    private final String codigo;

    UnidadMedida(int id, String nombre, String codigo) {
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCodigo() {
        return codigo;
    }

}
