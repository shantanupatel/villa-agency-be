package com.villa_agency;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.villa_agency.media.MediaService;

@SpringBootApplication
public class VillaAgencyBe {

	private MediaService mediaService;

	public static void main(String[] args) {
		SpringApplication.run(VillaAgencyBe.class, args);
	}

	// @Bean
	// SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	// http.csrf(AbstractHttpConfigurer::disable)
	// .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
	// authorizationManagerRequestMatcherRegistry
	// .requestMatchers("/**").permitAll().anyRequest().authenticated())
	// .httpBasic(Customizer.withDefaults())
	// .sessionManagement(httpSecuritySessionManagementConfigurer ->
	// httpSecuritySessionManagementConfigurer
	// .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
	//
	// return http.build();
	// }

	@Bean
	ModelMapper getModelMapper() {
		return new ModelMapper();
	}

	public void run(String... arg) throws Exception {
		// storageService.deleteAll();
		mediaService.init();
	}
}
