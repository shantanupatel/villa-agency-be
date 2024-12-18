package com.villa_agency;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import com.villa_agency.media.MediaService;

@SpringBootApplication
public class VillaAgencyBe {

	private MediaService mediaService;

	public static void main(String[] args) {
		SpringApplication.run(VillaAgencyBe.class, args);
	}

	// @Bean
	// UrlBasedCorsConfigurationSource corsConfigurationSource() {
	// CorsConfiguration configuration = new CorsConfiguration();
	// configuration.setAllowedOrigins(Arrays.asList("https://example.com"));
	// configuration.setAllowedMethods(Arrays.asList("GET", "POST"));
	// UrlBasedCorsConfigurationSource source = new
	// UrlBasedCorsConfigurationSource();
	// source.registerCorsConfiguration("/**", configuration);
	// return source;
	// }
	//
	// @Bean
	// SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
	// return
	// http.authorizeExchange().anyExchange().authenticated().and().httpBasic().and().formLogin();
	// return http.build();
	// }

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry
						//
						// .requestMatchers(HttpMethod.DELETE).hasRole("ADMIN").requestMatchers("/admin/**").hasAnyRole("ADMIN")
						// .requestMatchers("/user/**").hasAnyRole("USER",
						// "ADMIN").requestMatchers("/login/**").permitAll()
						.requestMatchers("/**").permitAll().anyRequest().authenticated())
				.httpBasic(Customizer.withDefaults())
				.sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		return http.build();
	}

	@Bean
	ModelMapper getModelMapper() {
		return new ModelMapper();
	}

	public void run(String... arg) throws Exception {
		// storageService.deleteAll();
		mediaService.init();
	}
}
