package com.assembleia.votacao.repository;

import com.assembleia.votacao.domain.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface PautaRepository extends JpaRepository<Pauta, Long> {


    Optional<Pauta> findByTitulo(String titulo);
}
