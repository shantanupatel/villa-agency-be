package com.villa_agency.socialplatform;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.villa_agency.response.GenericResponse;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class SocialPlatformController {

	private SocialPlatformService socialPlatformService;

	public SocialPlatformController(SocialPlatformService socialPlatformService) {
		super();
		this.socialPlatformService = socialPlatformService;
	}

	@PostMapping(path = "/admin/social-platforms")
	public GenericResponse<String> createSocialPlatforms(@RequestBody SocialPlatform socialPlatform) {
		socialPlatformService.createSocialPlatform(socialPlatform);

		return new GenericResponse<String>(HttpStatus.CREATED.value(), "New social link created successfully", null);
	}

	@GetMapping(path = "/all/social-platforms")
	public GenericResponse<List<SocialPlatform>> getSocialPlatforms() {
		List<SocialPlatform> socialPlatforms = socialPlatformService.getSocialPlatforms();

		return new GenericResponse<>(HttpStatus.OK.value(), "List of social platforms retrieved successfully",
				socialPlatforms);
	}

}
