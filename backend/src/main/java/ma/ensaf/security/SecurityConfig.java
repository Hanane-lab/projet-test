package ma.ensaf.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableMethodSecurity
public class SecurityConfig {


private final JwtFilter jwtFilter;


public SecurityConfig(JwtFilter jwtFilter) {
this.jwtFilter = jwtFilter;
}


@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
http.csrf().disable()
.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
.and()
.authorizeHttpRequests()
.requestMatchers("/auth/**", "/public/**").permitAll()
.requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
.requestMatchers("/organisateur/**").hasAuthority("ROLE_ORGANISATEUR")
.requestMatchers("/client/**").hasAuthority("ROLE_CLIENT")
.anyRequest().authenticated();


http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
return http.build();
}


@Bean
public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
return authConfig.getAuthenticationManager();
}
}