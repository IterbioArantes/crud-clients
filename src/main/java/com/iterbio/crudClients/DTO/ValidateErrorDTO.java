package com.iterbio.crudClients.DTO;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@SuperBuilder
public class ValidateErrorDTO extends ErrorResponseDTO{

    private final List<FieldMessageDTO> validationList = new ArrayList<>();

    public void addValidationList(String field, String message){
        this.validationList.add(FieldMessageDTO.builder()
                        .field(field)
                        .message(message)
                        .build());
    }
}