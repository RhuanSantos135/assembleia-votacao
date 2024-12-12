package com.assembleia.votacao.controller;


import com.assembleia.votacao.DTO.InPautaDTO;
import com.assembleia.votacao.DTO.OutPautaDTO;
import com.assembleia.votacao.domain.Pauta;
import com.assembleia.votacao.domain.Usuario;
import com.assembleia.votacao.service.PautaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/pauta")
public class PautaController {

    @Autowired
    private PautaService pautaService;



    @GetMapping("/{id}")
    public OutPautaDTO buscarPauta(@PathVariable Long id ){
        return pautaService.buscarPauta(id);
    }

    @Operation(summary = "Criar Pauta", description = "Cria uma nova pauta no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Pauta criada com sucesso.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Pauta.class))),
            @ApiResponse(responseCode = "400",
                    description = "Nenhuma pauta cadastrada no sistema. Verifique e tente novamente.")
    })
    @PostMapping("/criar")
    public OutPautaDTO criarPauta(@RequestBody InPautaDTO inPautaDTO)   {
        return pautaService.criaPauta(inPautaDTO);
    }

    @Operation(summary = "Abrir sessão", description = "Essa funcionalidade tem como base abrir uma sessão para a pauta informada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Sessão inserida com sucesso.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Pauta.class))),
            @ApiResponse(responseCode = "400",
                    description = "A pauta não encontrada no banco de dados!")
    })
    @PostMapping("/sessao")
    public OutPautaDTO inserirSessao(@RequestBody InPautaDTO inPautaDTO){
        return pautaService.inserirSessao(inPautaDTO);
    }

}
