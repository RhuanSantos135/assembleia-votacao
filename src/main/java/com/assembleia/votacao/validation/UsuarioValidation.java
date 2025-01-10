package com.assembleia.votacao.validation;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.assembleia.votacao.domain.Usuario;
import com.assembleia.votacao.domain.ZipCodeStackLocalAddress;
import com.assembleia.votacao.domain.ZipCodeStackResponse;
import com.assembleia.votacao.exceptions.BadRequestException;
import com.assembleia.votacao.service.ZipCodeStackService;
import org.springframework.stereotype.Component;

import static com.assembleia.votacao.utils.Utils.*;


@Component
public class UsuarioValidation {

    public static void validaCampos(Usuario usuario){
        validateName(usuario.getNome());
        validateEmail(usuario.getEmail());
        validatePassword(usuario.getSenha());
    }

}
