package com.rivertorres.Inventario.Inventario.Models;

import com.rivertorres.Inventario.Inventario.DTO.Producto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "inventario")
@Data
@NoArgsConstructor
public class Inventario {

    @Id
    @Column(name = "id_inventario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "cantidad")
    private float cantidad;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_producto")
    private Producto producto;

}
