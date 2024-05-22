package com.iterbio.crudClients.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(of = {"id"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tb_client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    private String name;
    @Column(unique = true)
    private String cpf;
    private double income;
    private LocalDate birthDate;
    private Integer children;
}