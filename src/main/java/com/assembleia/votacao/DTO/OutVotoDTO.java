package com.assembleia.votacao.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OutVotoDTO {

    private Long id;
    private Long idAssociado;
    private Long idPauta;
    private Boolean votosSimNao;
}
