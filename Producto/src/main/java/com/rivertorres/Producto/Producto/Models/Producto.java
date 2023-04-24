package com.rivertorres.Producto.Producto.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "producto")
@Data
@NoArgsConstructor
public class Producto {

    @Id
    @Column(name = "id_producto")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "marca", nullable = false, length = 100)
    private String marca;

    @Column(name = "codigo", nullable = false, length = 25)
    private String codigo;

    @Column(name = "nombre", nullable = false, length = 75)
    private String nombre;

    @Column(name = "precio", nullable = false)
    private double precio;

    @Enumerated(EnumType.STRING)
    @Column(name = "unidad_medida", nullable = false)
    private UnidadMedida unidadMedida = UnidadMedida.LITRO;
}
