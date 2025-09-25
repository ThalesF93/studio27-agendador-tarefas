package io.github.thalesf93.mappers;


import io.github.thalesf93.dto.EmployeesDTO;
import io.github.thalesf93.entities.Employee;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface EmployeesMapper {


    EmployeesDTO toDTO(Employee employee);


    @Mapping(source = "name", target = "name")// usado quando os campos tem nomes diferentes
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "cpf", target = "cpf")
    Employee toEntity(EmployeesDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDTO(EmployeesDTO dto, @MappingTarget Employee entity);

}
