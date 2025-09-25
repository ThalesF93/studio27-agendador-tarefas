package io.github.thalesf93.mappers;

import io.github.thalesf93.dto.EmployeesDTO;
import io.github.thalesf93.dto.ServicesDTO;
import io.github.thalesf93.entities.Employee;
import io.github.thalesf93.entities.Services;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ServicesMapper {

    ServicesDTO toDTO(Services services);

    Services toEntity(ServicesDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDTO(ServicesDTO dto, @MappingTarget Services entity);
}
