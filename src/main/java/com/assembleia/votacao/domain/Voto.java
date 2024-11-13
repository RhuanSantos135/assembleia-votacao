package com.assembleia.votacao.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity

public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único do voto", example = "1")
    private Long id;
    @Schema(description = "Identificador do associado que realizou o voto", example = "11")
    private Long idAssociado;
    @Schema(description = "Identificador da pauta votada", example = "1")
    private Long idPauta;
    }
