package com.assembleia.votacao.repository;

import com.assembleia.votacao.domain.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {

     Voto findByIdAssociadoAndIdPauta(Long idAssociado, Long idPauta);


     List<Voto> findByIdPauta(Long idPauta);
}

