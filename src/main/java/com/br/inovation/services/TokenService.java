package com.br.inovation.services;

import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.br.inovation.models.Usuario;
import com.auth0.jwt.JWT;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    public String getToken(Usuario usuario) {
        try{

            return JWT.create()
                    .withIssuer("delivery")
                    .withSubject(usuario.getLogin())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(LocalDateTime.now()
                            .plusMinutes(10)
                            .toInstant(ZoneOffset.of("-03:00"))
                    ).sign(Algorithm.HMAC256("chavesecreta"));
        }catch (JWTCreationException exception){
            throw new RuntimeException("erro ao gerar o token", exception);
        }

    }
    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256("chavesecreta");
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception){
            return "";
        }
    }

}
