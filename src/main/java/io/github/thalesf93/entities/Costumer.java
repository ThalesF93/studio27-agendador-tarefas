package io.github.thalesf93.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;
import java.util.UUID;

@Entity
@Table(name = "costumers")
@Data
public class Costumer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", unique = true)
    @Email
    private String email;

    @Column(name = "phone", nullable = false, unique = true)
    private String phone;

    @Column(name = "cpf", unique = true)
    @CPF
    private String cpf;

    @Column(name = "address")
    private String address;

    @ManyToOne
    @JoinColumn(name = "id_employee")
    private Employee employee;
}
