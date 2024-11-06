package com.assembleia.votacao.controller;


import com.assembleia.votacao.domain.Pauta;
import com.assembleia.votacao.service.PautaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/pauta")
public class PautaController {

    @Autowired
    private PautaService pautaService;


    @GetMapping("/{id}")
    public Pauta buscarPauta(@PathVariable Long id ){
        return pautaService.buscarPauta(id);
    }

    @PostMapping
    public Pauta criarPauta(@RequestBody Pauta pauta){
        return pautaService.criaPauta(pauta);
    }

    @PostMapping("/sessao")
    public Pauta inserirSessao(@RequestBody Pauta pauta){
        return pautaService.inserirSessao(pauta);
    }

}
