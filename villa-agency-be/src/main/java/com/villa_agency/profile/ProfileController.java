package com.villa_agency.profile;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/profiles")
@CrossOrigin(origins = "http://localhost:3000")
public class ProfileController {

	private ProfileService profileService;

	public ProfileController(ProfileService profileService) {
		super();
		this.profileService = profileService;
	}

	// CREATE
	@PostMapping
	public ResponseEntity<Profile> createProfile(@RequestBody Profile profile) {
		System.out.println(profile);

		Profile savedProfile = profileService.createProfile(profile);

		// return new ResponseEntity<Profile>(savedProfile, HttpStatus.OK);
		return ResponseEntity.ok(savedProfile);
	}

	// READ - get all
	@GetMapping
	public ResponseEntity<List<Profile>> getAllProfiles() {
		return ResponseEntity.ok(profileService.getAllProfiles());
	}

	// READ - get by id
	@GetMapping("/{id}")
	public ResponseEntity<Profile> getProfileById(@PathVariable Long id) {
		return profileService.getProfileById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	// UPDATE
	@PutMapping("/{id}")
	public ResponseEntity<Profile> updateProfile(@PathVariable Long id, @RequestBody Profile updatedProfile) {
		return profileService.updateProfile(id, updatedProfile).map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	// DELETE
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProfile(@PathVariable Long id) {
		boolean deletedProfile = profileService.deleteProfile(id);

		return deletedProfile ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
	}

}
