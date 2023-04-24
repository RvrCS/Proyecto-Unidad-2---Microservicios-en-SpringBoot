package com.rivertorres.venta.Venta.Repos;

import com.rivertorres.venta.Venta.Models.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Repository
public interface VentaRepo extends JpaRepository<Venta, Long> {

    Optional<Venta> findByFolio(String folio);

    List<Venta> findByFecha(LocalDate fecha);

    List<Venta> findByClienteId(Integer idCliente);
}
