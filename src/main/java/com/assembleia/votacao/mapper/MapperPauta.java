package com.assembleia.votacao.mapper;


import com.assembleia.votacao.DTO.InPautaDTO;
import com.assembleia.votacao.DTO.OutPautaDTO;
import com.assembleia.votacao.domain.Pauta;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class MapperPauta {

    public static final MapperPauta INSTANCE = Mappers.getMapper(MapperPauta.class);
    public abstract Pauta converteParaPauta(InPautaDTO inPautaDTO);
    public abstract OutPautaDTO converteParaSaidaPauta(Pauta pauta);
}
