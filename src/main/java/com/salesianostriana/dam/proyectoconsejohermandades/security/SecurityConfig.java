package com.salesianostriana.dam.proyectoconsejohermandades.security;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
	private final UserDetailsService userDetailsService;
	private final PasswordEncoder passwordEncoder;
	
	@Bean
	public AuthenticationManager 
			authenticationManager(HttpSecurity http) throws Exception {
		
		AuthenticationManagerBuilder authBuilder =
				http.getSharedObject(AuthenticationManagerBuilder.class);
		
		return authBuilder
			.authenticationProvider(daoAuthenticationProvider())
			.build();
		
		
	}
	
	@Bean 
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder);
		return provider;
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
			.antMatchers("/css/**","/js/**","/webjars/**", "/h2-console/**", "/img/**", "/font/**").permitAll()
			.antMatchers("/admin/**").hasRole("ADMIN")
			.anyRequest().authenticated()
			.and()
		.formLogin()
            .loginPage("/login")
            .successHandler(new CustomAuthenticationSuccessHandler())
            .permitAll()
            .and()
        .logout()
            .permitAll();
		
		// Añadimos esto para poder seguir accediendo a la consola de H2
		// con Spring Security habilitado.
		http.csrf().disable();
		http.headers().frameOptions().disable();
		
		return http.build();
	}
	
	public static class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

        @Override
        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                            Authentication authentication) throws IOException, ServletException {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            authorities.forEach(authority -> {
                if (authority.getAuthority().equals("ROLE_ADMIN")) {
                    try {
                        response.sendRedirect("/admin/");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else if(authority.getAuthority().equals("ROLE_USER")) {
                	try {
                        response.sendRedirect("/");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {
                
                    throw new IllegalStateException("Role not supported: " + authority.getAuthority());
            
                }
            });
        }
    }

}
