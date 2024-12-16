package com.assembleia.votacao.mapper;


import com.assembleia.votacao.domain.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.Optional;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class MapperUserDetails {

    public static final MapperUserDetails INSTANCE = Mappers.getMapper(MapperUserDetails.class);
    public abstract Usuario converteParaUserDetails(Optional<Usuario> userDetails);

}
