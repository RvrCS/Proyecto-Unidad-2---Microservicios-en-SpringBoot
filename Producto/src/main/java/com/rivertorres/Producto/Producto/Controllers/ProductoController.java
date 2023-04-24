package com.rivertorres.Producto.Producto.Controllers;

import com.rivertorres.Producto.Producto.Models.Producto;
import com.rivertorres.Producto.Producto.ProductoApplication;
import com.rivertorres.Producto.Producto.Services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/")
    public String getPage(){
        return "Welcome to Producto-service";
    }

    @GetMapping("/productos")
    public List<Producto> getAllProductos(){
        return productoService.getAllProductos();
    }

    @GetMapping("/{id}")
    public Producto getProductoById(@PathVariable Integer id){
        return productoService.getProductoById(id);
    }

    @GetMapping("/marca/{marca}")
    public List<Producto> getProductoByMarca(@PathVariable String marca){
        return productoService.getProductosByMarca(marca);
    }

    @GetMapping("/codigo/{codigo}")
    public Producto getProductoByCodigo(@PathVariable String codigo){
        return productoService.getProductoByCodigo(codigo);
    }

    @PostMapping("/save")
    public Producto createProducto(@RequestBody Producto producto){
        return productoService.createProducto(producto);
    }

    @PutMapping("/{id}")
    public Producto updateProducto(@PathVariable Integer id, @RequestBody Producto producto){
        return productoService.updateProducto(id, producto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProducto(@PathVariable Integer id){
        if(productoService.deleteProducto(id)){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
