package com.assembleia.votacao.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class OutUserDTO {
    private Long idAssociado;
    private String nome;
    private String email;
    private String postal_code;
    private String city_en;
    private String state_en;
}
