package com.assembleia.votacao.repository;

import com.assembleia.votacao.domain.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long> {
    Pauta findFirstByOrderByPrazoPautaDesc();

}
