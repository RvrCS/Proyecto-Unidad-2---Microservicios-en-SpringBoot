package com.rivertorres.venta.Venta.Models;

import com.rivertorres.venta.Venta.DTO.Cliente;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "venta")
@Data
@NoArgsConstructor
public class Venta {
    @Id
    @Column(name = "id_venta")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "total", nullable = false)
    private float total;

    @Column(name = "folio", nullable = false, length = 255)
    private String folio;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Detalle> detalles = new ArrayList<>();

}
