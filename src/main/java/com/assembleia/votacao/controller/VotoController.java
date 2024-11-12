package com.assembleia.votacao.controller;


import com.assembleia.votacao.domain.Voto;
import com.assembleia.votacao.service.Votoservice;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/voto")
public class VotoController {

    @Autowired
    private Votoservice votoservice;


    @PostMapping("/cadastra")
    public Voto inserirVoto(@RequestBody Voto voto){
        return votoservice.inserirVoto(voto);
    }
    }


