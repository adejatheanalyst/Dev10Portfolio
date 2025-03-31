package learn.safari.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final JwtConverter converter;

    public SecurityConfig(JwtConverter converter) {
        this.converter = converter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationConfiguration authConfig) throws Exception {
        // we're not using HTML forms in our app
        //so disable CSRF (Cross Site Request Forgery)
        http.csrf(csrf -> csrf.disable());

        // this configures Spring Security to allow
        //CORS related requests (such as preflight checks)
        http.cors(cors -> cors.configure(http));

        // the order of the requestMatchers() method calls is important
        // as they're evaluated in the order that they're added
        http.authorizeHttpRequests(auth -> auth
                // new...
                .requestMatchers("/authenticate").permitAll()
                .requestMatchers("/refresh_token").authenticated()
                .requestMatchers("/create_account").permitAll()
                .requestMatchers(HttpMethod.GET,
                        "/order").permitAll()
                .requestMatchers(HttpMethod.GET,
                        "/sighting", "/sighting/*").permitAll()
                .requestMatchers(HttpMethod.POST,
                        "/sighting").hasAnyAuthority("USER", "ADMIN")
                .requestMatchers(HttpMethod.PUT,
                        "/sighting/*").hasAnyAuthority("USER", "ADMIN")
                .requestMatchers(HttpMethod.DELETE,
                        "/sighting/*").hasAnyAuthority("ADMIN")
                // if we get to this point, let's deny all requests
                .requestMatchers("/**").denyAll());

        http.addFilter(new JwtRequestFilter(authenticationManager(authConfig), converter));
        http.sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
