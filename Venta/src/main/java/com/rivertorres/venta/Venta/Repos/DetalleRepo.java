package com.rivertorres.venta.Venta.Repos;

import com.rivertorres.venta.Venta.Models.Detalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleRepo extends JpaRepository<Detalle, Integer> {

    List<Detalle> findByVentaId(Integer id);

}
