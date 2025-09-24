package io.github.thalesf93.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "employees")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Employees {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    @Email
    private String email;

    @Column
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "cpf")
    @CPF
    @JsonFormat(pattern = "111.111.111-56")
    private String cpf;

    @Column(name = "address")
    private String address;

    @Column
    @CreatedDate
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate registrationDate;

    @Type(ListArrayType.class)//traduz list para array
    @Column(name = "roles", columnDefinition = "varchar[]")
    private List<String> roles;

    //Setando o E-mail inserido como login do empregado
    public void setLogin(String login) {
        this.login = this.email;
    }
}
