package uz.uzpartner.infoapp.config;


import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import uz.uzpartner.infoapp.config.jwt.JwtFilter;
import uz.uzpartner.infoapp.entity.enums.Role;
import uz.uzpartner.infoapp.service.UDService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true)
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UDService udService;
    private final PasswordEncoder passwordEncoder;
    private final JwtFilter jwtFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().and().csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/v2/api-docs",
                        "/*",
                        "/swagger-resources",
                        "/swagger-resources/**",
                        "/configuration/ui",
                        "/configuration/security",
                        "/swagger-ui/**",
                        "/webjars/**")
                .permitAll()
                .antMatchers(HttpMethod.POST, "/api/auth/**").permitAll()
                .antMatchers( "/api/test/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/user-management/**").hasAnyAuthority(Role.ADMIN.name())
                .antMatchers(HttpMethod.PATCH, "/api/user-management/**").hasAnyAuthority(Role.ADMIN.name())
                .antMatchers(HttpMethod.POST, "/api/user-management/**").hasAnyAuthority(Role.ADMIN.name())
                .antMatchers(HttpMethod.GET, "/api/user-management/**").hasAnyAuthority(Role.ADMIN.name())
                .antMatchers(HttpMethod.GET, "/api/company/**").hasAnyAuthority(Role.MODERATOR.name())
                .antMatchers(HttpMethod.POST, "/api/company/**").hasAnyAuthority(Role.MODERATOR.name())
                .antMatchers(HttpMethod.PUT, "/api/company/**").hasAnyAuthority(Role.MODERATOR.name())
                .anyRequest().authenticated();

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(udService).passwordEncoder(passwordEncoder);
    }


}
