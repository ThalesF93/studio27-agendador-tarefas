package io.github.thalesf93.mappers;

import io.github.thalesf93.dto.CostumersDTO;
import io.github.thalesf93.entities.Costumer;
import io.github.thalesf93.repository.EmployeesRepository;
import lombok.Getter;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

//transforma em um component para spring
@Mapper(componentModel = "spring", uses = EmployeesMapper.class)//Utiliza outro mapper quando exisitir outro objeto a ser mapeado de outro mapper
@Getter
public abstract class CostumersMapper {

    @Autowired
    EmployeesRepository employeesRepository;


    // Metodo que retorna a entidade
    //sempre passar como paramatro o objeto que será convertido
    @Mapping(target = "employee",
            expression = "java(dto.idEmployee() == null ? null : employeesRepository.findById(dto.idEmployee()).orElse(null))")
    public abstract Costumer toEntity(CostumersDTO dto);


    public abstract CostumersDTO toDTO(Costumer costumer);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Costumer updateFromDTO(CostumersDTO dto, @MappingTarget Costumer entity);
}
