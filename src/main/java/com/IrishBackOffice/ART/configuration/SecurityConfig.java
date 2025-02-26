package com.IrishBackOffice.ART.configuration;

import com.IrishBackOffice.ART.iservice.UsuarioService;
import com.IrishBackOffice.ART.jwt.JwtAuthenticationFilter;
import com.IrishBackOffice.ART.jwt.JwtAuthorizationFilter;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    /**
     * @param usuarioService
     * @param passwordEncoder
     * @return 
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider(UsuarioService usuarioService,
            BCryptPasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(usuarioService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    /**
     * @param http
     * @param authProvider
     * @return 
     * @throws java.lang.Exception 
     */
    @Bean
    public AuthenticationManager authManager(HttpSecurity http,
            DaoAuthenticationProvider authProvider) throws Exception {
        return http
                .getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(authProvider)
                .build();
    }

    /**
     * @param http
     * @param authManager
     * @return 
     * @throws java.lang.Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
            AuthenticationManager authManager)
            throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .formLogin(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                // Endpoints públicos
                .requestMatchers("/api/auth/**", "/js/**", "/css/**", "/img/**").permitAll()
                // El resto, con token
                .anyRequest().authenticated()
                );

        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

        http
                .addFilter(new JwtAuthenticationFilter(authManager, key))
                .addFilter(new JwtAuthorizationFilter(authManager, key));

        return http.build();
    }
}
