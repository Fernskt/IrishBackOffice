package com.IrishBackOffice.ART.configuration;

import com.IrishBackOffice.ART.iservice.UsuarioService;
import com.IrishBackOffice.ART.jwt.JwtAuthenticationFilter;
import com.IrishBackOffice.ART.jwt.JwtAuthorizationFilter;
import com.IrishBackOffice.ART.service.UsuarioServiceImpl;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    
    @Autowired 
    UsuarioServiceImpl usuarioService;

    /**
     * Configuración del proveedor de autenticación
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
     * Configuración del AuthenticationManager
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
     * Configuración de CORS
     * @return 
     */
@Bean
public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOriginPatterns(Arrays.asList("*"));
    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH",  "DELETE", "OPTIONS"));
    configuration.setAllowedHeaders(Arrays.asList("*"));
    configuration.setAllowCredentials(true);
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
}


    /**
     * Configuración del SecurityFilterChain
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
                .cors(Customizer.withDefaults()) // Habilitar CORS
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
                .addFilter(new JwtAuthenticationFilter(authManager, key, usuarioService))
                .addFilter(new JwtAuthorizationFilter(authManager, key));

        return http.build();
    }
}
