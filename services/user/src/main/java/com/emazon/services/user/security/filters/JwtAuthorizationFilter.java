package com.emazon.services.user.security.filters;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.emazon.services.user.util.JWTUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Override //This methode is executed for each request
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(!request.getServletPath().equals(JWTUtil.REFRESH_TOKEN_ROUTE)){
            String authorizationToken = request.getHeader(JWTUtil.AUTH_HEADER);
            if (authorizationToken != null && authorizationToken.startsWith(JWTUtil.BEARER_TOKEN_PREFIX)) {
                try {
                    createAuthToken(request, response, filterChain, authorizationToken);
                }
                catch (Exception e){
                    response.setHeader("error-message",e.getMessage());
                    response.sendError(HttpServletResponse.SC_FORBIDDEN);
                }
            }
        }
        filterChain.doFilter(request,response);
    }

    private void createAuthToken(HttpServletRequest request,
                                 HttpServletResponse response,
                                 FilterChain filterChain,
                                 String authorizationToken) throws IOException, ServletException {
        DecodedJWT decodedJWT = JWTUtil.getDecodedJWT(authorizationToken);

        String username = decodedJWT.getSubject();
        String[] roles = decodedJWT
                .getClaim("roles")
                .asArray(String.class);

        Collection<GrantedAuthority> authorities = getGrantedAuthorities(roles);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username,null,authorities);

        SecurityContextHolder
                .getContext()
                .setAuthentication(authenticationToken);

        filterChain.doFilter(request, response);
    }

    private Collection<GrantedAuthority> getGrantedAuthorities(String[] roles) {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        for(String role : roles){
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }
}
