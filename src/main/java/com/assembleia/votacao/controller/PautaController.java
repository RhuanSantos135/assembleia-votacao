package com.assembleia.votacao.controller;


import com.assembleia.votacao.domain.Pauta;
import com.assembleia.votacao.service.PautaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller

@RestController
@RequestMapping("/pauta")
public class PautaController {

    @Autowired
    private PautaService pautaService;


    @GetMapping("/{id}")
    public Pauta buscarPauta( @PathVariable Long id ){
        return pautaService.buscarPauta(id);
    }


    @PostMapping
    public Pauta criarPauta(@RequestBody Pauta pauta) throws IllegalAccessException{
        return pautaService.criaPauta(pauta);
    }

}
