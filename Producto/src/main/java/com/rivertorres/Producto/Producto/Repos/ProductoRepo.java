package com.rivertorres.Producto.Producto.Repos;

import com.rivertorres.Producto.Producto.Models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepo extends JpaRepository<Producto, Integer> {

    List<Producto> findByMarca(String marca);

    Optional<Producto> findByCodigo(String codigo);

}
