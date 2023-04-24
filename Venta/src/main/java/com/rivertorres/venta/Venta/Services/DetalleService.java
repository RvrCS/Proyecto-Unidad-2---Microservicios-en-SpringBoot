package com.rivertorres.venta.Venta.Services;

import com.rivertorres.venta.Venta.DTO.Producto;
import com.rivertorres.venta.Venta.Models.Detalle;
import com.rivertorres.venta.Venta.Models.Venta;
import com.rivertorres.venta.Venta.Repos.DetalleRepo;
import com.rivertorres.venta.Venta.Repos.VentaRepo;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class DetalleService {

    @Autowired
    private DetalleRepo detalleRepo;

    @Autowired
    private VentaRepo ventaRepo;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${productoService.url}")
    private String productoServiceUrl;

    public List<Detalle> getAllDetalles() {
        List<Detalle> detalles = detalleRepo.findAll();
        for(Detalle detalle : detalles){
            int idProducto = detalle.getProducto().getId();
            Producto producto = getProductoById(idProducto);
            detalle.setProducto(producto);
        }

        return detalles;
    }

    public Detalle getDetalleById(Integer id) {
        return detalleRepo.findById(id).orElse(null);
    }

    public Detalle createDetalle(Detalle detalle, Integer idProducto, long idVenta) {
        Producto producto = getProductoById(idProducto);
        if(producto == null) return null;
        Venta venta = ventaRepo.findById(idVenta).orElse(null);
        if(venta == null) return null;

        detalle.setProducto(producto);
        detalle.setVenta(venta);
        return detalleRepo.save(detalle);
    }

    public Detalle updateDetalle(Integer id, Detalle detalle) {
        Detalle detalleExistente = detalleRepo.findById(id).orElse(null);
        if (detalleExistente == null) {
            return null;
        }
        detalleExistente.setVenta(detalle.getVenta());
        detalleExistente.setProducto(detalle.getProducto());
        detalleExistente.setCantidad(detalle.getCantidad());
        return detalleRepo.save(detalleExistente);
    }

    public boolean deleteDetalleVentaById(Integer id){
            try {
                detalleRepo.deleteById(id);
                return true;
            } catch (Exception ex) {
                return false;
            }

    }

    private Producto getProductoById(Integer id) {
        ResponseEntity<Producto> response = restTemplate.getForEntity(productoServiceUrl+"/"+id, Producto.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            return null;
        }
    }

}
