package com.assembleia.votacao.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Usuario  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único do associado", example = "1")
    private Long idAssociado;
    @Schema(description = "Nome completo do associado", example = "Rhuan Lucas")
    private String nome;
    @Schema(description = "Endereço de email do associado", example = "rhuan.lucas@gmail.com")
    private String email;
    @Schema(description = "Senha de acesso do associado (criptografada)", example = "hashed_password123")
    private String senha;
    @Schema(description = "Codigo postal da cidade", example = "37218000")
    private String postal_code;
    @Schema(description = "Nome da cidade", example = "Ijaci")
    private String city_en;
    @Schema(description = "Nome do Estado ", example = "Minas Gerais")
    private String state_en;
}
