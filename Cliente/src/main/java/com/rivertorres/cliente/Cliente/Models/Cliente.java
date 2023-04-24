package com.rivertorres.cliente.Cliente.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "cliente")
@Data
@NoArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Cliente")
    private int id;


    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "correo", nullable = false, length = 100)
    private String correo;

    @Column(name = "RFC", nullable = false, length = 100)
    private String RFC;

    @Column(name = "telefono", nullable = false, length = 100)
    private String telefono;

    @Column(name = "contraseña", nullable = false, length = 100)
    private String contraseña;
}
