package com.emazon.services.user.security;

import com.emazon.services.user.entity.UserCredentials;
import com.emazon.services.user.security.filters.JwtAuthenticationFilter;
import com.emazon.services.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userService ;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                UserCredentials userCredentials = userService.loadUserByUsername(username);
                Collection<GrantedAuthority> authorities = new ArrayList<>();
                userCredentials.getRolesOfUser().forEach(r->{
                    authorities.add(new SimpleGrantedAuthority(r.getRolesName()));
                });
                return new User(userCredentials.getUsername(),userCredentials.getPassword(),authorities);
            }
        });
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable(); // to enable stateless authentication
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.headers().frameOptions().disable();//inactive frame security
        http.authorizeRequests().antMatchers("/h2-console/**").permitAll();//enable access to h2 without authentication
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(new JwtAuthenticationFilter(authenticationManager()));
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
