package com.emazon.services.user.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JWTUtil {
    public static final String SECRET = "mySecret1234";
    public static final String AUTH_HEADER = "Authorization";
    public static final long EXPIRE_ACCES_TOKEN = 10 * 60 * 1000;
    public static final long EXPIRE_REFRESH_TOKEN = 15 * 60 * 1000;
    public static final String PREFIXTOKEN = "Bearer ";
    public static final String REFRESHTOKENROOT = "/refreshToken";


    private static JWTCreator.Builder generateToken(String username, String requestURL, Date expireDate) {
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(expireDate)
                .withIssuer(requestURL);
    }

    public static String generateAccessToken(String username, String requestURL, List<String> roles) {
        return generateToken(username, requestURL, new Date(System.currentTimeMillis() + JWTUtil.EXPIRE_ACCES_TOKEN))
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

}
