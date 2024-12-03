package com.assembleia.votacao.mapper;


import com.assembleia.votacao.DTO.InUserDTO;
import com.assembleia.votacao.DTO.InVotoDTO;
import com.assembleia.votacao.DTO.OutUserDTO;
import com.assembleia.votacao.DTO.OutVotoDTO;
import com.assembleia.votacao.domain.Usuario;
import com.assembleia.votacao.domain.Voto;
import jakarta.security.auth.message.MessagePolicy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class MapperVoto {

    public static final MapperVoto INSTANCE = Mappers.getMapper(MapperVoto.class);
    public abstract Voto converteParaVoto(InVotoDTO inVotoDTO);
    public abstract OutVotoDTO converteParaSaidaVoto(Voto voto);


}
