package com.emazon.services.inventory.security;

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
    public static final long EXPIRE_ACCES_TOKEN = 2 * 60 * 1000;
    public static final long EXPIRE_REFRESH_TOKEN = 15 * 60 * 1000;
    public static final String PREFIXTOKEN = "Bearer ";
    public static final String REFRESHTOKENROOT = "/refreshToken";


    private static JWTCreator.Builder generateToken(String username, String requestURL, Date expireDate) {
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(expireDate)
                .withIssuer(requestURL);
    }



}
