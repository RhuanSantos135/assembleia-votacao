package com.assembleia.votacao.security.jwt;

import com.assembleia.votacao.service.UsuarioDetalheImplementacao;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JWTutils {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiracaoMs}")
    private int jwtExpiracaoMs;


    public String geracaoTokenUser(UsuarioDetalheImplementacao usuarioDetalheImplementacao) {
        return Jwts.builder()
                .setSubject(usuarioDetalheImplementacao.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiracaoMs))
                .signWith(getKeyassinada(), SignatureAlgorithm.HS512)
                .compact();
    }


    private Key getKeyassinada() {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
        return key;
    }

    public boolean validacaoJWTToken(String authToken) {
        try {
            Jwts.parser()
                    .setSigningKey(getKeyassinada())
                    .build()
                    .parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException e) {
            System.out.println("Token inválido: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro ao validar o token: " + e.getMessage());
        }
        return false;
    }
}
