package com.assembleia.votacao.service;


import com.assembleia.votacao.DTO.InPautaDTO;
import com.assembleia.votacao.DTO.OutPautaDTO;
import com.assembleia.votacao.domain.Pauta;
import com.assembleia.votacao.exceptions.BadRequestException;
import com.assembleia.votacao.exceptions.ObjectNotFoundException;
import com.assembleia.votacao.mapper.MapperPauta;
import com.assembleia.votacao.repository.PautaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PautaService {
    @Autowired
    private PautaRepository repository;

    @Autowired
    private MapperPauta mapperPauta;

    public OutPautaDTO buscarPauta(Long id) {
        var pauta = repository.findById(id);
        if (pauta.isEmpty()) {
            throw new ObjectNotFoundException("Nenhuma pauta cadastrada no sistema. Verifique e tente novamente.");
        } else {
            return mapperPauta.converteParaSaidaPauta(pauta.get());
        }
    }

    public OutPautaDTO criaPauta(InPautaDTO inPautaDTO) {
        var pauta = mapperPauta.converteParaPauta(inPautaDTO);
        return mapperPauta.converteParaSaidaPauta(repository.save(pauta));
    }

    public Pauta deletaPauta(Long id) {
        var pauta = repository.findById(id);
        if (pauta.isPresent()) {
            repository.deleteById(id);
            return pauta.get();
        } else {
            throw new ObjectNotFoundException("Pauta não existe");
        }
    }

    public OutPautaDTO inserirSessao(InPautaDTO inPautaDTO) {
        var pauta = mapperPauta.converteParaPauta(inPautaDTO);
        var response = repository.findById(pauta.getId());

        if (response.isPresent()) {
            var pautaExistente = response.get();
            if (pautaExistente.getPrazoPauta() == null) {
                if (pauta.getPrazoPauta() != null) {
                    pautaExistente.setPrazoPauta(pauta.getPrazoPauta());
                } else {
                    var data = LocalDateTime.now();
                    pautaExistente.setPrazoPauta(data.plusMinutes(1));
                }
                return mapperPauta.converteParaSaidaPauta(repository.save(pauta));
            } else {
                throw new IllegalStateException("Votação em andamento. Não é possível alterar o prazo.");
            }
        }
        throw new ObjectNotFoundException("Pauta não encontrada no banco de dados!");
    }
}
