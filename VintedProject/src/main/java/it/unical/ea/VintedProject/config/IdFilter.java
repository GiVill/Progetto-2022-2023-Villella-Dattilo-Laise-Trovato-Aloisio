package it.unical.ea.VintedProject.config;

import com.nimbusds.jose.JWSObject;
import com.nimbusds.jwt.JWTClaimsSet;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import it.unical.ea.VintedProject.core.detail.LoggedUserDetail;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static org.keycloak.representations.idm.CredentialRepresentation.SECRET;
@AllArgsConstructor
public class IdFilter extends OncePerRequestFilter {
    private JwtAuthConverter jwtAuthConverter;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println(LoggedUserDetail.getInstance().getIdKeycloac());
    }

}
