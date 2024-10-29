package com.assembleia.votacao.service;


import com.assembleia.votacao.domain.Pauta;
import com.assembleia.votacao.domain.Voto;
import com.assembleia.votacao.repository.PautaRepository;
import com.assembleia.votacao.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Votoservice {

    @Autowired
    private PautaRepository pautaRepository;

    @Autowired
    private VotoRepository votoRepository;

    public ResponseEntity<Voto> registraVoto(Voto voto){
        Voto novoVoto = votoRepository.save(voto);

        Optional<Pauta> pautaOptional = pautaRepository.findById(Long.valueOf(voto.getIdPauta()));
        if (pautaOptional.isPresent()){
            Pauta pauta = pautaOptional.get();
            pauta.setNumerosVotos(pauta.getNumerosVotos() + 1 );
            pautaRepository.save(pauta);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoVoto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }


    }
}
