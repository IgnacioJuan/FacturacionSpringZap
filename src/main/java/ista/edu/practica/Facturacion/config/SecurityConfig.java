// package ista.edu.practica.Facturacion.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.web.SecurityFilterChain;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {

//     @Bean
//     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//         http
//                 .headers(headers -> {
//                     headers.contentSecurityPolicy(
//                             "default-src 'self'; script-src 'self' https://trusted.cdn.com; report-uri /csp-report-endpoint");
//                     headers.xssProtection();
//                     headers.contentTypeOptions();
//                     headers.frameOptions().deny();
//                     headers.httpStrictTransportSecurity()
//                             .includeSubDomains(true)
//                             .maxAgeInSeconds(31536000);
//                 })
//                 .csrf(csrf -> csrf.disable()) // Revisa si realmente necesitas deshabilitar CSRF
//                 // .authorizeRequests(auth -> auth.anyRequest().authenticated());
//                 .authorizeRequests(auth -> {
//                     auth.antMatchers("/public").permitAll();
//                     auth.antMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll(); // Permitir acceso a Swagger
//                     auth.anyRequest().authenticated();
//                 });
//         return http.build();
//     }
// }