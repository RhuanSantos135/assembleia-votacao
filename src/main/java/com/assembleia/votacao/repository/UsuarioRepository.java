package com.assembleia.votacao.repository;

import com.assembleia.votacao.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {


    Usuario findByEmail(String email);

}
