package com.emazon.services.user.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.emazon.services.user.entity.Role;
import com.emazon.services.user.entity.UserCredentials;
import com.emazon.services.user.util.JWTUtil;
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
        String refreshToken = request.getHeader("Authorization");
        if (refreshToken != null && refreshToken.startsWith(JWTUtil.BEARER_TOKEN_PREFIX)) {
            try {
                DecodedJWT decodedJWT = JWTUtil.getDecodedJWT(refreshToken);
                String username = decodedJWT.getSubject();

                UserCredentials userCredentials = userService.loadUserByUsername(username);
                List<String> roles = userCredentials.getRolesOfUser().stream().map(Role::getRolesName).collect(Collectors.toList());
                String jwtAccessToken = JWTUtil.generateAccessToken(userCredentials.getUsername(),
                        request.getRequestURL().toString(),
                        roles);

                JWTUtil.sendTokens(JWTUtil.unBearerToken(refreshToken),jwtAccessToken,response);
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
