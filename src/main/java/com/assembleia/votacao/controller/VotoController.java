package com.assembleia.votacao.controller;


import com.assembleia.votacao.DTO.ResultadoDTO;
import com.assembleia.votacao.domain.Voto;
import com.assembleia.votacao.service.Votoservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/voto")
public class VotoController {

    @Autowired
    private Votoservice votoservice;


    @PostMapping("/cadastra")
    public Voto inserirVoto(@RequestBody Voto voto){
        return votoservice.inserirVoto(voto);
    }

    @GetMapping("/result/{id}")
    public ResultadoDTO calculaResultado(@PathVariable Long id) {
        return votoservice.calcularResultado(id);
    }

}


