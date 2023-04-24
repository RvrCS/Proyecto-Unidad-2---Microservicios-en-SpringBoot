package com.rivertorres.venta.Venta.Controllers;

import com.rivertorres.venta.Venta.Models.Detalle;
import com.rivertorres.venta.Venta.Services.DetalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detalle")
public class DetalleController {

    @Autowired
    private DetalleService detalleService;

    @GetMapping("/")
    public String getPage(){
        return "Welcome to detalle-service";
    }

    @GetMapping("/detalles")
    public List<Detalle> getAllDetalles() {
        return detalleService.getAllDetalles();
    }

    @GetMapping("/{id}")
    public Detalle getDetalleById(@PathVariable Integer id) {
        return detalleService.getDetalleById(id);
    }

    @PostMapping("/save")
    public Detalle createDetalle(@RequestBody Detalle detalle, @RequestParam Integer idProducto, @RequestParam long idVenta) {
        return detalleService.createDetalle(detalle, idProducto, idVenta);
    }

    @PutMapping("/{id}")
    public Detalle updateDetalle(@PathVariable Integer id, @RequestBody Detalle detalle) {
        return detalleService.updateDetalle(id, detalle);
    }

    @DeleteMapping("/{id}")
    public boolean deleteDetalle(@PathVariable Integer id) {
        return detalleService.deleteDetalleVentaById(id);
    }
}
