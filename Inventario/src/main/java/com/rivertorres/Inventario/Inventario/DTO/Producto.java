package com.rivertorres.Inventario.Inventario.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Producto {


    @Id
    @Column(name = "id_producto")
    private Integer id;
    @Transient
    private String marca;

    @Transient
    private String codigo;

    @Transient
    private String nombre;

    @Transient
    private double precio;

}
