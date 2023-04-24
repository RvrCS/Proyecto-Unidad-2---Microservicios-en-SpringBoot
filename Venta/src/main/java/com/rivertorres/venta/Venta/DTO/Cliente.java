package com.rivertorres.venta.Venta.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Cliente {

    @Id
    @Column(name = "id_cliente")
    private int id;

    @Transient
    private String nombre;

    @Transient
    private String correo;

    @Transient
    private String RFC;
    @Transient
    private String telefono;
    @Transient
    private String contraseña;
}
