package br.com.dh.meli.desafiofinal.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.servlet.http.HttpServletResponse;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/v1/client/**", "/api/v1/seller/**").permitAll()
                .antMatchers( "/api/v1/fresh-products/inboundorder/**", "/api/v1/seller/**").hasAnyRole("SELLER", "ADMIN")
                .antMatchers(HttpMethod.POST,"/api/v1/fresh-products/**").hasAnyRole("SELLER", "ADMIN")
                .antMatchers( "/api/v1/client/**", "/api/v1/purchase-order/**").hasAnyRole("CLIENT", "ADMIN")
                .antMatchers(HttpMethod.GET, "/api/v1/fresh-products/**", "/api/v1/category/**").permitAll()
                .antMatchers( "/api/v1/role/**", "/api/v1/warehouse/**", "/api/v1/category").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and().httpBasic()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, ex) -> {
                            response.sendError(
                                    HttpServletResponse.SC_UNAUTHORIZED,
                                    ex.getMessage()
                            );
                        }
                )
                .and().build();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
