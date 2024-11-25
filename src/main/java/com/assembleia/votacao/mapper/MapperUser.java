package com.assembleia.votacao.mapper;


import com.assembleia.votacao.DTO.InUserDTO;
import com.assembleia.votacao.DTO.OutUserDTO;
import com.assembleia.votacao.domain.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class MapperUser {

    public static final MapperUser INSTANCE = Mappers.getMapper(MapperUser.class);

    public  abstract Usuario converteParaUsuaruio(InUserDTO inUserDTO);

    public  abstract OutUserDTO converteParaSaidaUsuario(Usuario usuario);


}
