package com.villa_agency.auth;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.villa_agency.payload.request.LoginRequest;
import com.villa_agency.payload.request.SignupRequest;
import com.villa_agency.payload.response.JwtResponse;
import com.villa_agency.response.GenericResponse;
import com.villa_agency.role.ERole;
import com.villa_agency.role.Role;
import com.villa_agency.role.RoleRepository;
import com.villa_agency.security.jwt.JwtUtils;
import com.villa_agency.security.services.UserDetailsImpl;
import com.villa_agency.user.User;
import com.villa_agency.user.UserRepository;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
  UserRepository userRepository;

	@Autowired
  RoleRepository roleRepository;

	@Autowired
  PasswordEncoder encoder;

	@Autowired
  JwtUtils jwtUtils;

	@PostMapping("/signin")
	public GenericResponse<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		if (authentication.getAuthorities().isEmpty()) {
			return new GenericResponse<>(HttpStatus.UNAUTHORIZED.value(), "Invalid credentials", null);
		}

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);


		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		// return ResponseEntity
		// .ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(),
		// userDetails.getEmail(), roles));

		return new GenericResponse<>(HttpStatus.OK.value(), "Login successful",
				new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));

	}

	@PostMapping("/signup")
	public GenericResponse<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			// return ResponseEntity.badRequest().body(new MessageResponse("Error: Username
			// is already taken!"));
			return new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), "Username is already taken", null);
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			// return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is
			// already in use!"));
			return new GenericResponse<>(HttpStatus.BAD_REQUEST.value(), "Email is already in use!", null);
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				System.out.println("Role: " + role);

				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "mod":
					Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		// return ResponseEntity.ok(new MessageResponse("User registered
		// successfully!"));

		return new GenericResponse<>(HttpStatus.CREATED.value(), "User registered successfully!", null);
	}
}
