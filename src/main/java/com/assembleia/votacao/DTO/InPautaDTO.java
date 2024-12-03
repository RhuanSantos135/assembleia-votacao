package com.assembleia.votacao.DTO;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class InPautaDTO {

    private String descricao;
    private LocalDateTime prazoPauta;

}
