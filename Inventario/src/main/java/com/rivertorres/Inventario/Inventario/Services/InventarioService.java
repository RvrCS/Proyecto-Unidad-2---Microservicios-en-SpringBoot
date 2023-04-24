package com.rivertorres.Inventario.Inventario.Services;

import com.rivertorres.Inventario.Inventario.DTO.Producto;
import com.rivertorres.Inventario.Inventario.Models.Inventario;
import com.rivertorres.Inventario.Inventario.Repos.InventarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class InventarioService {

    @Autowired
    private InventarioRepo inventarioRepo;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${productoService.url}")
    private String productoServiceUrl;

    public List<Inventario> getAllInventario() {
        List<Inventario> inventarios = inventarioRepo.findAll();
        for(Inventario inventario : inventarios){
            int idProducto = inventario.getProducto().getId();
            Producto producto = getProductoById(idProducto);
            inventario.setProducto(producto);
        }

        return inventarios;
    }

    public Inventario getInventarioById(Integer id) {

        return inventarioRepo.findById(id).orElse(null);
    }

    public Optional<Inventario> getInventarioByProductoId(Integer productoId) {
        Optional<Inventario> inventario = inventarioRepo.findByProductoId(productoId);
        if (inventario.isPresent()) {
            return inventario;
        } else {
            Producto producto = restTemplate.getForObject(productoServiceUrl +"/"+ productoId, Producto.class);
            if (producto != null) {
                Inventario nuevoInventario = new Inventario();
                nuevoInventario.setCantidad(0);
                nuevoInventario.setProducto(producto);
                inventarioRepo.save(nuevoInventario);
                return Optional.of(nuevoInventario);
            } else {
                return Optional.empty();
            }
        }
    }


    public Inventario createInventario(Inventario inventario) {

        return inventarioRepo.save(inventario);
    }

    public Inventario saveInventario(Inventario inventario) {
        return inventarioRepo.save(inventario);
    }

    public boolean deleteInventario(Integer inventarioId) {
        Optional<Inventario> inventario = inventarioRepo.findById(inventarioId);
        if (inventario.isPresent()) {
            inventarioRepo.delete(inventario.get());
            return true;
        } else {
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
