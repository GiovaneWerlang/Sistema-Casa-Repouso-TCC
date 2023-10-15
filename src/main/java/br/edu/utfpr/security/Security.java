package br.edu.utfpr.security;

import br.edu.utfpr.enums.Funcao;
import io.smallrye.jwt.build.Jwt;
import org.eclipse.microprofile.jwt.Claims;

import java.time.Instant;
import java.util.Arrays;
import java.util.HashSet;

public class Security {

    public String token(Funcao role, String nome, String id, Instant instant){
        return Jwt.issuer("https://tymed.com/security")
                .subject(id)
                .audience("http://localhost:4200")
                .upn("tymed@tymed")
                .groups(new HashSet<>(Arrays.asList(role.toString())))
                .claim(Claims.full_name, nome)
                .expiresAt(instant)
                .sign();
    }
}
