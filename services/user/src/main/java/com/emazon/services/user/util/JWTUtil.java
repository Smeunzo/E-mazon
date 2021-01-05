package com.emazon.services.user.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JWTUtil{

    public final static String SECRET = "MIGeMA0GCSqGSIb3DQEBAQUAA4GMADCBiAKBgHhPWZ8yew0ty5itxcaIJccUcKZY";
    public final static long EXPIRE_ACCESS_TOKEN = 120_000;
    public final static long EXPIRE_REFRESH_TOKEN = 86_400_000;
    public static final String AUTH_HEADER = "Authorization";
    public static final String BEARER_TOKEN_PREFIX = "Bearer ";
    public static final String REFRESH_TOKEN_ROUTE = "/users/refreshToken";
    public static final String REGISTER_USER_ROUTE = "/users/registerUser";


    private static JWTCreator.Builder generateToken(String username, String requestURL, Date expireDate) {
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(expireDate)
                .withIssuer(requestURL);
    }

    public static String generateAccessToken(String username, String requestURL, List<String> roles) {
        return generateToken(username, requestURL, new Date(System.currentTimeMillis() + JWTUtil.EXPIRE_ACCESS_TOKEN))
                .withClaim("roles", roles)
                .sign(Algorithm.HMAC256(SECRET));
    }

    public static String generateRefreshToken(String username, String requestURL) {
        return generateToken(username, requestURL, new Date(System.currentTimeMillis() + JWTUtil.EXPIRE_REFRESH_TOKEN))
                .sign(Algorithm.HMAC256(SECRET));
    }

    public static void sendTokens(String jwtRefreshToken, String jwtAccessToken, HttpServletResponse response) throws IOException {
        Map<String, String> idToken = new HashMap<>();
        idToken.put("access-token", jwtAccessToken);
        idToken.put("refresh-token", jwtRefreshToken);
        response.setContentType("application/json");
        new ObjectMapper().writeValue(response.getOutputStream(), idToken);
        response.setHeader(JWTUtil.AUTH_HEADER, jwtAccessToken);
    }

    public static JWTVerifier getVerifier() {
        Algorithm algorithm = Algorithm.HMAC256(JWTUtil.SECRET);
        return JWT.require(algorithm).build();
    }

    public static DecodedJWT getDecodedJWT(String authorizationToken) {
        String token = unBearerToken(authorizationToken); // remove "Bearer" from Authorization's header
        JWTVerifier jwtVerifier = JWTUtil.getVerifier();
        return jwtVerifier.verify(token);
    }

    public static String unBearerToken(String authorizationToken) {
        return authorizationToken.substring(JWTUtil.BEARER_TOKEN_PREFIX.length());
    }
}
