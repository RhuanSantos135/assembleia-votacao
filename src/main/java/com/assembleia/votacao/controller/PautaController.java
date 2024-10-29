package com.assembleia.votacao.controller;


import com.assembleia.votacao.domain.Pauta;
import com.assembleia.votacao.service.PautaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller

@RestController
@RequestMapping("/pauta")
public class PautaController {

    @Autowired
    private PautaService pautaService;


    @GetMapping
    public ResponseEntity getAllPautas(){
        var allPautas = pautaService.getAll();
        return ResponseEntity.ok(allPautas);
    }

    @PostMapping("create")
    public ResponseEntity createPauta(@RequestBody @Validated Pauta pauta){
        return pautaService.create(pauta);

    }
}
