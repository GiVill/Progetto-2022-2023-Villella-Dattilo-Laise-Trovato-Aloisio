package it.unical.ea.VintedProject.security;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//TODO Serve?
public class TokenStore {
    private final String secretKey = "23778sah9021-12123-12s-as-12a-AS_12xoiJN-SHWQ98"; // Random key: this should not be public!
    private final static TokenStore instance = new TokenStore();

    private TokenStore() {
    }

    public static TokenStore getInstance() {
        return instance;
    }

    public String createToken(Map<String, Object> claims) throws JOSEException {
        Instant issuedAt = Instant.now().truncatedTo(ChronoUnit.SECONDS); // Issued now
        Instant notBefore = issuedAt.plus(5, ChronoUnit.SECONDS); // Valid in 5 seconds (useless to us, here just to show how to set it)
        Instant expiration = issuedAt.plus(24, ChronoUnit.HOURS); // Invalid after 24 hours
        JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder();
        for (String entry : claims.keySet())
            builder.claim(entry, claims.get(entry));
        JWTClaimsSet claimsSet = builder.issueTime(Date.from(issuedAt))
                .notBeforeTime(Date.from(notBefore))
                .expirationTime(Date.from(expiration)).build();
        Payload payload = new Payload(claimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(new JWSHeader(JWSAlgorithm.HS256), payload);
        jwsObject.sign(new MACSigner(secretKey.getBytes()));
        return jwsObject.serialize();
    }

    public Long verifyToken(String token) throws JOSEException, ParseException {
        try {
            return getInsertion(token);
        } catch (RuntimeException e) {
            return null;
        }

    }

    public Long getInsertion(String token) throws JOSEException, ParseException {
        SignedJWT signedJWT = SignedJWT.parse(token);
        JWSVerifier jwsVerifier = new MACVerifier(secretKey.getBytes());
        if(signedJWT.verify(jwsVerifier) &&
                new Date().before(signedJWT.getJWTClaimsSet().getExpirationTime()) &&
                new Date().after(signedJWT.getJWTClaimsSet().getNotBeforeTime()))
            return (Long) signedJWT.getPayload().toJSONObject().get("id");
        throw new RuntimeException("Invalid token");
    }

    public String getToken(HttpServletRequest request) {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(header != null && header.startsWith("Bearer "))
            return header.replace("Bearer ", "");
        return "invalid";
    }

}

