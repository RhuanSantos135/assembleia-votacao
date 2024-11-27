package com.assembleia.votacao.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InVotoDTO {


    private Long idAssociado;
    private Long idPauta;
    private Boolean votosSimNao;


}
