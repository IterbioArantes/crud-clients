package com.iterbio.crudClients.services;

import com.iterbio.crudClients.DTO.ClientDTO;
import com.iterbio.crudClients.entities.Client;
import com.iterbio.crudClients.repositories.ClientRepository;
import com.iterbio.crudClients.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id){

        Optional<Client> client = clientRepository.findById(id);

        return entityToDTO(client.orElseThrow(() -> new ResourceNotFoundException(id)));
    }

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable){

        Page<Client> clients = clientRepository.findAll(pageable);

        return clients.map(this::entityToDTO);
    }

    @Transactional
    public ClientDTO insert(ClientDTO clientDTO){

        Client client = clientRepository.save(dtoToEntity(clientDTO));

        return entityToDTO(client);
    }

    @Transactional
    public ClientDTO update(Long id,ClientDTO clientDTO){

        try{
            Client client = clientRepository.getReferenceById(id);

            client = clientRepository.save(dtoToEntity(clientDTO,client));

            return entityToDTO(client);
        }catch(EntityNotFoundException e){
            throw new ResourceNotFoundException("to update",id);
        }
    }

    @Transactional
    public void delete(Long id){

        if(!clientRepository.existsById(id)){
            throw new ResourceNotFoundException("to delete",id);
        }
        clientRepository.deleteById(id);
    }

    public Client dtoToEntity(ClientDTO clientDTO){

        return Client.builder()
                .id(clientDTO.getId())
                .name(clientDTO.getName())
                .cpf(clientDTO.getCpf())
                .income(clientDTO.getIncome())
                .birthDate(clientDTO.getBirthDate())
                .children(clientDTO.getChildren())
                .build();
    }

    public Client dtoToEntity(ClientDTO clientDTO,Client existingClient){

        existingClient.setName(clientDTO.getName());
        existingClient.setCpf(clientDTO.getCpf());
        existingClient.setIncome(clientDTO.getIncome());
        existingClient.setBirthDate(clientDTO.getBirthDate());
        existingClient.setChildren(clientDTO.getChildren());

        return existingClient;
    }

    public ClientDTO entityToDTO(Client client){

        return ClientDTO.builder()
                .id(client.getId())
                .name(client.getName())
                .cpf(client.getCpf())
                .income(client.getIncome())
                .birthDate(client.getBirthDate())
                .children(client.getChildren())
                .build();
    }
}