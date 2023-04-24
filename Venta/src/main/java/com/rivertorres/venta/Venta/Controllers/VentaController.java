package com.rivertorres.venta.Venta.Controllers;

import com.rivertorres.venta.Venta.Models.Venta;
import com.rivertorres.venta.Venta.Services.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @GetMapping("/")
    public String getPage(){
        return "Welcome to Venta-service";
    }


    @GetMapping("/ventas")
    public List<Venta> getAllVentas() {
        return ventaService.getAllVentasClienteInfo();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> getVentaById(@PathVariable Long id) {
        Venta venta = ventaService.getVentaById(id);
        if (venta == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(venta);
    }

    @GetMapping("/folio/{folio}")
    public ResponseEntity<Venta> getVentaByFolio(@PathVariable String folio) {
        Venta venta = ventaService.getVentaByFolio(folio);
        if (venta == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(venta);
    }

    @GetMapping("/fecha/{fecha}")
    public List<Venta> getVentasByFecha(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fecha) {
        return ventaService.getVentasByFecha(fecha);
    }

    @GetMapping("/cliente/{idCliente}")
    public List<Venta> getVentasByClienteId(@PathVariable Integer idCliente) {
        return ventaService.getVentasByClienteId(idCliente);
    }

    @PostMapping("/save")
    public ResponseEntity<Venta> createVenta(@RequestBody Venta venta, @RequestParam Long idCliente) {
        Venta ventaCreada = ventaService.createVenta(venta, idCliente);
        if (ventaCreada == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.created(URI.create("/ventas/" + ventaCreada.getId())).body(ventaCreada);
    }

    /*@PutMapping("/{id}")
    public ResponseEntity<Venta> updateVenta(@PathVariable Integer id, @RequestBody Venta venta) {
        Venta ventaActualizada = ventaService.updateVenta(id, venta);
        if (ventaActualizada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ventaActualizada);
    }*/

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVenta(@PathVariable Long id) {
        if (!ventaService.deleteVenta(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
