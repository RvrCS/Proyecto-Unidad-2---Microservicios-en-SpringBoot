package com.rivertorres.cliente.Cliente.Services;

import com.rivertorres.cliente.Cliente.Models.Cliente;
import com.rivertorres.cliente.Cliente.Repos.ClienteRepo;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    //Dependecy injection of ClienteRepo
    @Autowired
    private ClienteRepo clienteRepo;

    //Get a list of all the clients
    public List<Cliente> getAllClients(){
        return clienteRepo.findAll();
    }

    //Get a Cliente by its ID
    public Cliente getClienteById(Integer id){
        return clienteRepo.findById(id).orElse(null);
    }

    //Get a Client by its RFC
    public Cliente getClienteByRfc(String rfc){
        return clienteRepo.findByRFC(rfc).orElse(null);
    }

    //Create a Client
    public Cliente createCliente(Cliente cliente){
        return clienteRepo.save(cliente);
    }

    //Updates a cliente
    public Cliente updateCliente(Integer id, Cliente cliente){
        Cliente clienteExists = clienteRepo.findById(id).orElse(null);
        if(clienteExists == null) return null;

        clienteExists.setRFC(cliente.getRFC());
        clienteExists.setNombre(cliente.getNombre());
        clienteExists.setCorreo(cliente.getCorreo());
        clienteExists.setTelefono(cliente.getTelefono());
        return clienteRepo.save(clienteExists);

    }

    //Delets a Cliente
    public boolean deleteCliente(Integer id){
        try{
            clienteRepo.deleteById(id);
            return true;
        }catch (Exception ex){
            return false;
        }

    }
}
