package com.assembleia.votacao.service;


import com.assembleia.votacao.domain.Pauta;
import com.assembleia.votacao.repository.PautaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class PautaService {
    @Autowired
    private PautaRepository repository;

    public Pauta buscarPauta(Long id ){
        Optional<Pauta> pauta =  repository.findById(id);
        if (pauta.isEmpty()){
            throw new RuntimeException("Nenhuma pauta cadastrada no sistema. Verifique e tente novamente.");
        } else {
          return pauta.get();
        }
    }

    public Pauta criaPauta(Pauta pauta){
       return repository.save(pauta);
    }

    public Pauta deletaPauta(Long id){
        var pauta = repository.findById(id);
        if(pauta.isPresent()){
            repository.deleteById(id);
            return pauta.get();
        }else {
            throw new RuntimeException("Pauta não existe");
        }
    }

    public Pauta inserirSessao(Pauta pauta){
                var response = repository.findById(pauta.getId());
                if(response.isPresent()){
                    if(response.get().getPrazoPauta() == null) {
                        if (pauta.getPrazoPauta() != null) {
                            response.get().setPrazoPauta(pauta.getPrazoPauta());
                            return repository.save(response.get());
                        } else {
                            var data = LocalDateTime.now();
                            response.get().setPrazoPauta(data.plusMinutes(1));
                            return repository.save(response.get());
                        }
                    }
                    throw new RuntimeException("Votação em andamento.");
        }
        throw new RuntimeException("A pauta não encontrada no banco de dados!");
    }
}
