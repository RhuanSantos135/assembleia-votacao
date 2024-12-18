package com.assembleia.votacao.service;


import com.assembleia.votacao.DTO.AccessDTO;
import com.assembleia.votacao.DTO.AuthenticationDTO;
import com.assembleia.votacao.security.jwt.JWTutils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTutils jwTutils;


    public AccessDTO login(AuthenticationDTO authenticationDTO){

        try {


            UsernamePasswordAuthenticationToken userAuth =
                    new UsernamePasswordAuthenticationToken(authenticationDTO.getNome(), authenticationDTO.getSenha());

            Authentication authentication = authenticationManager.authenticate(userAuth);

            UserDetails userAuthenticate = (UserDetails) authentication.getPrincipal();

            String token  = jwTutils.geracaoTokenUser((UsuarioDetalheImplementacao) userAuthenticate);

            var accessDto = new AccessDTO(token);
        }
        catch (BadCredentialsException e) {
            // Implementar
        }
        //Implementar
        return null;
    }

}
