package com.assembleia.votacao.exceptions;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {

    private int statusCode;
    private String mensagem;
    private LocalDateTime data;



}
