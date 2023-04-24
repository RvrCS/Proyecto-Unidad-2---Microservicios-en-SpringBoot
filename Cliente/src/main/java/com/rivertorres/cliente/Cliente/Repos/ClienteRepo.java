package com.rivertorres.cliente.Cliente.Repos;

import com.rivertorres.cliente.Cliente.Models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ClienteRepo extends JpaRepository<Cliente, Integer> {

    Optional<Cliente> findByRFC(String rfc);

}
