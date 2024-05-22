package com.iterbio.crudClients.controllers;

import com.iterbio.crudClients.DTO.ClientDTO;
import com.iterbio.crudClients.entities.Client;
import com.iterbio.crudClients.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable Long id){

        ClientDTO clientDTO = clientService.findById(id);

        return ResponseEntity.ok(clientDTO);
    }
    @GetMapping
    public ResponseEntity<Page<ClientDTO>> findById(Pageable pageable){

        return ResponseEntity.ok(clientService.findAll(pageable));
    }
    @PostMapping
    public ResponseEntity<ClientDTO> insert(@Valid @RequestBody ClientDTO clientDTO){

        ClientDTO clientInserted = clientService.insert(clientDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clientInserted.getId()).toUri();

        return ResponseEntity.created(uri).body(clientInserted);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> update(@PathVariable Long id, @Valid @RequestBody ClientDTO clientDTO){

        return ResponseEntity.ok(clientService.update(id,clientDTO));
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){

        clientService.delete(id);

        return ResponseEntity.noContent().build();
    }
}