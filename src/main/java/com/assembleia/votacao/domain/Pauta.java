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

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Pauta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único da pauta", example = "1")
    private Long id;
    @Schema(description = "Descrição detalhada da pauta", example = "Reunião Estratégica de Planejamento para 2024")
    private String descricao;
    @Schema(description = "Data e hora limite para discussão e votação da pauta", example = "2024-12-31T23:59:59")
    private LocalDateTime prazoPauta;


}

