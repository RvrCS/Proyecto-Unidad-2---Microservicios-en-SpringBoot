package com.rivertorres.Inventario.Inventario.Controller;

import com.rivertorres.Inventario.Inventario.Models.Inventario;
import com.rivertorres.Inventario.Inventario.Services.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @GetMapping("/")
    public String getPage(){
        return "Welcome to inventario-service";
    }

    @GetMapping("/{productoId}")
    public ResponseEntity<Inventario> getInventarioByProductoId(@PathVariable Integer productoId) {
        Optional<Inventario> inventario = inventarioService.getInventarioByProductoId(productoId);
        if (inventario.isPresent()) {
            return ResponseEntity.ok(inventario.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Inventario> saveInventario(@RequestBody Inventario inventario) {
        Inventario inventarioGuardado = inventarioService.saveInventario(inventario);
        return ResponseEntity.created(URI.create("/inventarios/" + inventarioGuardado.getId())).body(inventarioGuardado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventario(@PathVariable Integer id) {
        if (!inventarioService.deleteInventario(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

}
