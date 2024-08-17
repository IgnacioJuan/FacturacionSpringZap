package ista.edu.practica.Facturacion.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.boot.web.servlet.FilterRegistrationBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Configuración de la cadena de filtros de seguridad
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Configuración de encabezados de seguridad
                .headers(headers -> {
                    // Política de seguridad de contenido (CSP)
                    headers.contentSecurityPolicy(
                            "default-src 'self'; script-src 'self' https://trusted.cdn.com; style-src 'self' https://trusted.cdn.com; img-src 'self' data:; frame-ancestors 'none'; form-action 'self'; report-to csp-endpoint;");
                    // Protección contra ataques XSS
                    headers.xssProtection(xss -> xss.block(true));
                    // Configuración de opciones de tipo de contenido
                    headers.contentTypeOptions();
                    // Configuración de opciones de marco
                    headers.frameOptions().deny();
                    // Configuración de seguridad estricta de transporte HTTP (HSTS)
                    headers.httpStrictTransportSecurity()
                            .includeSubDomains(true)
                            .maxAgeInSeconds(31536000);
                })
                // Deshabilitar protección CSRF (Cross-Site Request Forgery)
                .csrf(csrf -> csrf.disable()) 
                // Configuración de autorización de solicitudes
                .authorizeRequests(auth -> {
                    // Permitir acceso a la ruta "/public" sin autenticación
                    auth.antMatchers("/public").permitAll();
                    // Permitir acceso a Swagger UI y documentación de API sin autenticación
                    auth.antMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll();
                    // Permitir acceso a los endpoints de cliente sin autenticación
                    auth.antMatchers("/cliente/listar", "/cliente/crear", "/cliente/eliminar/**",
                            "/cliente/actualizar/**").permitAll();
                    // Requerir autenticación para cualquier otra solicitud
                    auth.anyRequest().authenticated();
                });
        return http.build();
    }

    // Registro del filtro para configurar SameSite en las cookies
    @Bean
    public FilterRegistrationBean<SameSiteCookieFilter> sameSiteCookieFilter() {
        FilterRegistrationBean<SameSiteCookieFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new SameSiteCookieFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }

    // Filtro para agregar atributos HttpOnly y SameSite=Strict a las cookies
    public class SameSiteCookieFilter implements Filter {
        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
                throws IOException, ServletException {
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            String header = httpServletResponse.getHeader("Set-Cookie");
            if (header != null) {
                httpServletResponse.setHeader("Set-Cookie", header + "; HttpOnly; SameSite=Strict");
            }
            chain.doFilter(request, response);
        }
    }
}