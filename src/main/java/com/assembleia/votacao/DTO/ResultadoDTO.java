package com.assembleia.votacao.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ResultadoDTO {
    private Long idPauta;
    private String descricaoPauta;
    private int votosSim;
    private int votosNao;
    private String resultado;

}
