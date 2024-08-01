package biblioteca.example.biblioteca.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(csrf -> csrf.disable())
                           .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                           .authorizeHttpRequests(authorise -> authorise
                           .requestMatchers(HttpMethod.GET,"/usuario/todos").hasRole("FUNCIONARIO")
                           .requestMatchers(HttpMethod.GET,"/usuario/{id}").permitAll()
                           .requestMatchers(HttpMethod.POST,"/usuario/login").permitAll()
                           .requestMatchers(HttpMethod.POST,"/usuario/cadastro").hasRole("FUNCIONARIO")
                           .requestMatchers(HttpMethod.PUT,"/usuario/{id}").hasRole("FUNCIONARIO")
                           .requestMatchers(HttpMethod.DELETE,"/usuario/{id}").hasRole("FUNCIONARIO")
                           .requestMatchers(HttpMethod.GET,"/item/**").permitAll()
                           .requestMatchers(HttpMethod.POST,"/item/**").hasRole("FUNCIONARIO")
                           .requestMatchers(HttpMethod.PUT,"/item/**").hasRole("FUNCIONARIO")
                           .requestMatchers(HttpMethod.DELETE,"/item/**").hasRole("FUNCIONARIO")
                           .anyRequest().authenticated()
                        ).addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class).build();
    }

    @Bean
	public AuthenticationManager AuthenticationManager(AuthenticationConfiguration configration) throws Exception {
		return configration.getAuthenticationManager();
	}
	
	@Bean
	public PasswordEncoder passwordencoder() {
		return new BCryptPasswordEncoder();
	}
}
