package com.emazon.services.user.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.emazon.services.user.entity.Role;
import com.emazon.services.user.entity.UserCredentials;
import com.emazon.services.user.security.JWTUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class JWTServiceImpl implements JWTService {

    private final UserService userService ;

    @Override
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authToken = request.getHeader("Authorization");
        if (authToken != null && authToken.startsWith(JWTUtil.PREFIXTOKEN)) {
            try {
                String refreshToken = authToken.substring(JWTUtil.PREFIXTOKEN.length());
                Algorithm algorithm1 = Algorithm.HMAC256(JWTUtil.SECRET);
                JWTVerifier jwtVerifier = JWT.require(algorithm1).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(refreshToken);
                String username = decodedJWT.getSubject();
                UserCredentials userCredentials = userService.loadUserByUsername(username);
                List<String> roles = userCredentials.getRolesOfUser().stream().map(Role::getRolesName).collect(Collectors.toList());
                String jwtAccessToken = JWTUtil.generateAccessToken(userCredentials.getUsername(),
                        request.getRequestURL().toString(),
                        roles);
                JWTUtil.sendTokens(refreshToken,jwtAccessToken,response);
            }
            catch (Exception e){
                throw e ;
            }

        }
        else{
            throw new RuntimeException("refresh token required");
        }
    }
}
