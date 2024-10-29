package com.assembleia.votacao.service;


import com.assembleia.votacao.domain.Pauta;
import com.assembleia.votacao.repository.PautaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PautaService {
    @Autowired
    private PautaRepository repository;


    public Pauta buscarPauta(Long id ){
        Optional<Pauta> pauta =  repository.findById(id);
        if (pauta.isEmpty()){
            throw new RuntimeException("Não tem pauta cadastrada");
        } else {
          return pauta.get();
        }
    }


    public Pauta criaPauta(Pauta pauta){
       return repository.save(pauta);
    }


}
