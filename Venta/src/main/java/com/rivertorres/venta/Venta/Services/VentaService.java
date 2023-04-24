package com.rivertorres.venta.Venta.Services;


import com.rivertorres.venta.Venta.DTO.Producto;
import com.rivertorres.venta.Venta.Models.Detalle;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.rivertorres.venta.Venta.DTO.Cliente;
import com.rivertorres.venta.Venta.Models.Venta;
import com.rivertorres.venta.Venta.Repos.VentaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VentaService {

    @Autowired
    private VentaRepo ventaRepo;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${clienteService.url}")
    private String clienteServiceUrl;

    @Value("${productoService.url}")
    private String productoServiceUrl;

    public List<Venta> getAllVentasClienteInfo() {
        List<Venta> ventas = ventaRepo.findAll();
        for(Venta venta : ventas){
            int idCliente = venta.getCliente().getId();
            Cliente cliente = getClienteById(idCliente);
            venta.setCliente(cliente);
        }
        return ventas;
    }

    public Venta getVentaById(Long id) {
        return ventaRepo.findById(id).orElse(null);
    }

    public Venta getVentaByFolio(String folio) {
        Venta venta = ventaRepo.findByFolio(folio).orElse(null);

        int idCliente = venta.getCliente().getId();
        Cliente cliente = getClienteById(idCliente);
        venta.setCliente(cliente);

        return venta;
    }

    public List<Venta> getVentasByFecha(LocalDate fecha) {
        return ventaRepo.findByFecha(fecha);
    }

    public List<Venta> getVentasByClienteId(Integer idCliente) {
        return ventaRepo.findByClienteId(idCliente);
    }

    public Venta createVenta(Venta venta, Long clienteId) {
        ResponseEntity<Cliente> response = restTemplate.getForEntity(clienteServiceUrl + "/" + clienteId, Cliente.class);

        if(response.getStatusCode() != HttpStatus.OK){
            return null;
        }

        Cliente cliente = response.getBody();
        venta.setCliente(cliente);
        return ventaRepo.save(venta);

    }

    public Venta updateVenta(Long id, Venta venta) {
        Venta ventaExistente = ventaRepo.findById(id).orElse(null);
        if (ventaExistente == null) {
            return null;
        }
        ventaExistente.setFolio(venta.getFolio());
        ventaExistente.setFecha(venta.getFecha());
        Cliente cliente = getClienteById(venta.getCliente().getId());

        if(cliente == null) return null;

        ventaExistente.setCliente(cliente);
        ventaExistente.getDetalles().clear();
        for (Detalle detalle : venta.getDetalles()){
            Producto producto = getProductoById(detalle.getProducto().getId());
            if (producto == null) return null;
            detalle.setVenta(ventaExistente);
            detalle.setProducto(producto);
            ventaExistente.getDetalles().add(detalle);
        }
        return ventaRepo.save(ventaExistente);
    }

    public boolean deleteVenta(Long id) {
        try {
            ventaRepo.deleteById(id);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }


    ////
    private Cliente getClienteById(Integer id) {
        ResponseEntity<Cliente> response = restTemplate.getForEntity(clienteServiceUrl +"/"+id, Cliente.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            return null;
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
