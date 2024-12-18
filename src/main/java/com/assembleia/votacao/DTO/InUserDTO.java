package com.assembleia.votacao.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

public class InUserDTO {

    private String nome;
    private String email;
    private String senha;
    private String postal_code;

}
