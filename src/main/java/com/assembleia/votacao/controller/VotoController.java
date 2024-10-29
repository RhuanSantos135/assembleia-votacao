package com.assembleia.votacao.controller;


import com.assembleia.votacao.domain.Voto;
import com.assembleia.votacao.service.Votoservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/voto")
public class VotoController {

    @Autowired
    private Votoservice votoservice;

    public ResponseEntity<Voto> registrarVoto(@RequestBody Voto voto) {
        return votoservice.registraVoto(voto);
    }

}
