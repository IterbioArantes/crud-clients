package com.iterbio.crudClients.repositories;

import com.iterbio.crudClients.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
