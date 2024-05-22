package com.iterbio.crudClients.DTO;

import com.iterbio.crudClients.validation.annotations.PastOrPresentWithOneYear;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ClientDTO {

    private Long id;

    @Size(min = 3, message = "The name requires at least 3 characters.")
    @Size(max = 80, message = "The maximum characters for the name is 80.")
    @NotBlank(message = "Name is required.")
    private String name;
    private String cpf;
    private Double income;

    @PastOrPresentWithOneYear
    private LocalDate birthDate;
    private Integer children;
}
