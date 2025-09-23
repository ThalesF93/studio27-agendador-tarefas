package io.github.thalesf93.mappers;


import io.github.thalesf93.dto.EmployeesDTO;
import io.github.thalesf93.entities.Employees;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeesMapper {


    EmployeesDTO toDTO(Employees employees);


    @Mapping(source = "name", target = "name")// usado quando os campos tem nomes diferentes
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "cpf", target = "cpf")
    Employees toEntity(EmployeesDTO dto);
}
