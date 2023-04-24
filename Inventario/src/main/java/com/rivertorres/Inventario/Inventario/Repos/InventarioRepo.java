package com.rivertorres.Inventario.Inventario.Repos;

import com.rivertorres.Inventario.Inventario.Models.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface InventarioRepo extends JpaRepository<Inventario, Integer> {

    Optional<Inventario> findByProductoId(Integer productoId);

}
