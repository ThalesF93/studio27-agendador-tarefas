package io.github.thalesf93.mappers;

import io.github.thalesf93.dto.ServicesDTO;
import io.github.thalesf93.entities.Services;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServicesMapper {

    ServicesDTO toDTO(Services services);

    Services toEntity(ServicesDTO dto);
}
