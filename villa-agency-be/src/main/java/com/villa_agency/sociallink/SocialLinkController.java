package com.villa_agency.sociallink;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.villa_agency.response.GenericResponse;

@RestController
// @RequestMapping(path = "/all/social")
@CrossOrigin(origins = "http://localhost:3000")
public class SocialLinkController {

	private SocialLinkService socialLinkService;

	public SocialLinkController(SocialLinkService socialLinkService) {
		super();
		this.socialLinkService = socialLinkService;
	}

	@PostMapping(path = "/admin/social-links")
	public GenericResponse<String> createNewSocialLink(@RequestBody SocialLink socialLink) {
		// socialLinkService.createSocialLink(socialLink);

		// return new GenericResponse<>(HttpStatus.CREATED.value(), "New social link
		// created successfully", null);

		socialLinkService.createSocialLink(socialLink);

		return new GenericResponse<>(HttpStatus.CREATED.value(), "New social link created successfully", null);
	}

	@GetMapping(path = "/all/social-links")
	public GenericResponse<List<SocialLink>> getSocialLinks() {
		List<SocialLink> links = socialLinkService.getSocialLinks();

		return new GenericResponse<>(HttpStatus.OK.value(), "List of social links retrieved successfully",
				links);
	}
}
