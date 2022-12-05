package com.security.jwt.config.jwt;

import com.security.jwt.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Collections;
import java.util.Date;

public class TokenUtil {

    private static final String HEADER = "Authoization";
    private static final String PREFIX = "Bearer ";
    private static final long EXPIRATION = 24*60*60*1000;
    private static final String SECRET_KEY = "q0&yti1ls0=2&s@zfuvd!2+h8o$4y60p%_r=^yf62eo#7lm6m1";
    private static final String EMISSOR = "DevNice";

    public static String criarToken(Usuario usuario){
        Key secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
        String token = Jwts.builder()
                .setSubject(usuario.getNome())
                .setIssuer(EMISSOR)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
        return PREFIX + token;
    }

    private static boolean isExpirationValid(Date expiration){
        return expiration.after(new Date(System.currentTimeMillis()));
    }

    private static boolean isEmissorValid(String emissor){
        return emissor.equals(EMISSOR);
    }

    private static boolean isSubjectValid(String username){
        return username != null && username.length() > 0;
    }

    public static Authentication validate(HttpServletRequest request){
        String token = request.getHeader(HEADER);
        token = token.replace(PREFIX, "");

        Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(SECRET_KEY.getBytes())
                .build().parseClaimsJws(token);

        String username = claimsJws.getBody().getSubject();
        String issuer = claimsJws.getBody().getIssuer();
        Date expira = claimsJws.getBody().getExpiration();

        if (isSubjectValid(username) && isEmissorValid(issuer) && isExpirationValid(expira)){
            return new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
        }

        return null;
    }
}
