package com.rivertorres.Producto.Producto.Services;

import com.rivertorres.Producto.Producto.Models.Producto;
import com.rivertorres.Producto.Producto.Models.UnidadMedida;
import com.rivertorres.Producto.Producto.Repos.ProductoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepo productoRepository;

    public List<Producto> getAllProductos() {

        return productoRepository.findAll();
    }

    public Producto getProductoById(Integer id) {
        return productoRepository.findById(id).orElse(null);
    }

    public List<Producto> getProductosByMarca(String marca) {

        return productoRepository.findByMarca(marca);
    }

    public Producto getProductoByCodigo(String codigo) {

        return productoRepository.findByCodigo(codigo).orElse(null);
    }

    public Producto createProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto updateProducto(Integer id, Producto producto) {
        Producto productoExistente = productoRepository.findById(id).orElse(null);
        if (productoExistente == null) {
            return null;
        }
        productoExistente.setMarca(producto.getMarca());
        productoExistente.setCodigo(producto.getCodigo());
        productoExistente.setNombre(producto.getNombre());
        productoExistente.setPrecio(producto.getPrecio());
        productoExistente.setUnidadMedida(UnidadMedida.KILOGRAMO);
        return productoRepository.save(productoExistente);
    }

    public boolean deleteProducto(Integer id) {
        try {
            productoRepository.deleteById(id);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
