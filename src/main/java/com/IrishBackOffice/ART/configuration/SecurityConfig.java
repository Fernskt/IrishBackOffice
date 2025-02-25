/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.IrishBackOffice.ART.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


/**
 *
 * @author Pc
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    
    
   @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .cors(cors -> {
                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                CorsConfiguration config = new CorsConfiguration();
                config.setAllowCredentials(true);
                config.addAllowedOriginPattern("*"); // Permitir todos los orígenes
                config.addAllowedHeader("*"); // Permitir todos los encabezados
                config.addAllowedMethod("*"); // Permitir todos los métodos
                source.registerCorsConfiguration("/**", config);
                cors.configurationSource(source);
            })
            .csrf(AbstractHttpConfigurer::disable)
            .httpBasic(Customizer.withDefaults())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))  // Cambiado a IF_REQUIRED o ALWAYS
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers( "/js/**", "/css/**", "/img/**").permitAll()
                .requestMatchers("/").permitAll()
                 .requestMatchers("/registro/**").permitAll()
                .anyRequest().permitAll()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/", true)
            )
            .logout(logout -> logout
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")        
            )
                .exceptionHandling(exceptionHandling ->
                exceptionHandling
                    .accessDeniedPage("/access-denied")
            );
        httpSecurity.httpBasic(AbstractHttpConfigurer::disable);

        return httpSecurity.build();
    }
//    
//    
//    @Bean
//    public AuthenticationManager authenticationManager( AuthenticationConfiguration authenticationConfiguration) throws Exception{
//       return authenticationConfiguration.getAuthenticationManager();
//    }
//    
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider(){
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setUserDetailsService(usuarioService);
//         provider.setPasswordEncoder(passwordEncoder());
//        return provider;
//    }
//   @Autowired
//    public void configure(AuthenticationManagerBuilder auth) throws Exception{
//        auth.authenticationProvider(authenticationProvider());
//    }
//    
//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails userDetails = User.withUsername("santiago")
//                .password("123456")
//                .roles("ADMIN")
//                .authorities("READ","CREATE")
//                .build();
//        
//        return new InMemoryUserDetailsManager(userDetails);
//    }
  
}
