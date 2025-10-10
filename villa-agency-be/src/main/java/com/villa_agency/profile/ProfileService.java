package com.villa_agency.profile;

import java.util.List;
import java.util.Optional;

public interface ProfileService {

	// CREATE
	Profile createProfile(Profile profile);

	// READ - get all
	List<Profile> getAllProfiles();

	// READ - get by id
	Optional<Profile> getProfileById(Long id);

	// UPDATE
	Optional<Profile> updateProfile(Long profileId, Profile profile);

	// DELETE
	boolean deleteProfile(Long id);

}
