package com.assembleia.votacao.controller;


import com.assembleia.votacao.domain.Voto;
import com.assembleia.votacao.service.Votoservice;
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

    @PostMapping("/voto")
    public Voto inserirVoto(@RequestBody Voto voto){
        return votoservice.inserirVoto(voto);
    }
    }


