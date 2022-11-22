package br.com.dh.meli.desafiofinal.config;

import br.com.dh.meli.desafiofinal.security.JwtTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {

    private final JwtTokenFilter jwtTokenFilter;
    //private final UserDetailsService userDetailsService;
    private final String[] authWhitelist = new String[]{
            // -- Swagger UI v3 (OpenAPI)
            "/swagger-ui/**",
            // -- our public API endpoints
            "/api/v1/user/login"
    };

    public SecurityConfig(JwtTokenFilter jwtTokenFilter) {
        this.jwtTokenFilter = jwtTokenFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
         http.csrf().disable()
                 .authorizeRequests()
                 .antMatchers(authWhitelist).permitAll()
                 .antMatchers(HttpMethod.POST,
                         "/api/v1/client",
                         "/api/v1/seller").permitAll()
                 .antMatchers(HttpMethod.GET,
                         "/api/v1/fresh-products/**",
                         "/api/v1/category/**",
                         "/api/v1/warehouse/**").permitAll()
                 .anyRequest().authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

         http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

         http.exceptionHandling().authenticationEntryPoint(
                (request, response, ex) -> {
                    response.sendError(
                            HttpServletResponse.SC_UNAUTHORIZED,
                            ex.getMessage()
                    );
                }
         );
        return http.build();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

//    @Bean
//    public AuthenticationManager authenticationManagerBean(HttpSecurity http) throws Exception {
//        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
//        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder());
//        return authenticationManagerBuilder.build();
//    }

//    @Bean
//    public JwtAuthenticationFilter getJWTAuthenticationFilter(AuthenticationManager authenticationManager) throws Exception {
//        final JwtAuthenticationFilter filter = new JwtAuthenticationFilter(authenticationManager, jwtTokenUtil);
//        //filter.setFilterProcessesUrl("/api/auth/login");
//        return filter;
//    }
}
