package com.iterbio.crudClients.services.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(Long id){
        super(String.format("Resource not found. Id: %s", id));
    }

    public ResourceNotFoundException(String msg,Long id){
        super(String.format("Resource not found %s. Id: %s", msg, id));
    }
}
