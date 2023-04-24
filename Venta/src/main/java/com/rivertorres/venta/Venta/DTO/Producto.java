package com.rivertorres.venta.Venta.DTO;

import jakarta.persistence.*;
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
