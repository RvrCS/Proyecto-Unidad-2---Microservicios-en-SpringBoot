package com.rivertorres.cliente.Cliente.Controllers;

import com.rivertorres.cliente.Cliente.Models.Cliente;
import com.rivertorres.cliente.Cliente.Services.ClienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClienteController {

    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);
    @Autowired
    private ClienteService clienteService;

    // GET MAPPINGS =======================================

    @GetMapping("/")
    public String getPage(){
        return "Welcome";
    }

    //Endpoint to get all the clients in a JSON file
    @GetMapping("/clientes")
    public List<Cliente> getAllClients(){
        return clienteService.getAllClients();
    }

    //Endpoint to get a specific client
    @GetMapping("/{id}")
    public Cliente getClientById(@PathVariable Integer id){
        return clienteService.getClienteById(id);
    }

    //Endpoint to get a Client by RFC
   @GetMapping("/rfc/{rfc}")
    public Cliente getClientByRfc(@PathVariable String rfc){
        return clienteService.getClienteByRfc(rfc);
    }

    //====================================================

    // Post Mapping

    //Endpoint to create a Client with JSON
    @PostMapping(value = "/save")
    public Cliente createClient(@RequestBody Cliente cliente){
        logger.debug("Se recibio el objeto cliente: {}", cliente);
        return clienteService.createCliente(cliente);
    }

    //=====================================================

    // Put Mapping

    //Endpoint to update a Client
    @PutMapping("/{id}")
    public Cliente updateClient(@PathVariable Integer id, @RequestBody Cliente cliente){
        return clienteService.updateCliente(id, cliente);
    }

    //=====================================================

    // Delete Mapping

    //Endpoint to delete a Client by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCliente(@PathVariable Integer id){
        if(clienteService.deleteCliente(id)){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }

    }

}
