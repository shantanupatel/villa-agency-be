package com.villa_agency.profile.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.villa_agency.profile.Profile;
import com.villa_agency.profile.ProfileRepository;
import com.villa_agency.profile.ProfileService;

@Service
public class ProfileServiceImpl implements ProfileService {

	private ProfileRepository profileRepo;

	public ProfileServiceImpl(ProfileRepository profileRepo) {
		super();
		this.profileRepo = profileRepo;
	}

	// @Override
	// public List<Profile> getProfileData() {
	// List<Profile> profileData = profileRepo.findAll().stream().toList();
	//
	// return profileData;
	// }
	//
	// @Override
	// public Profile updateProfile(Long profileId, Profile profile) {
	// Profile existingProfile = profileRepo.findById(profile.getId())
	// .orElseThrow(() -> new IdNotFoundException("Profile with id " +
	// profile.getId() + " not found"));
	//
	// existingProfile.setEmail(profile.getEmail());
	// existingProfile.setName(profile.getName());
	// existingProfile.setAddress(profile.getAddress());
	//
	// return profileRepo.save(existingProfile);
	// }

	// CREATE
	@Override
	public Profile createProfile(Profile profile) {
		return profileRepo.save(profile);
	}

	// READ - get all
	@Override
	public List<Profile> getAllProfiles() {
		return profileRepo.findAll();
	}

	// READ - get by id
	@Override
	public Optional<Profile> getProfileById(Long id) {
		// TODO Auto-generated method stub
		return profileRepo.findById(id);
	}

	// UPDATE
	@Override
	public Optional<Profile> updateProfile(Long profileId, Profile updatedProfile) {
		return profileRepo.findById(profileId).map(profile -> {
			profile.setName(updatedProfile.getName());
			profile.setEmail(updatedProfile.getEmail());
			profile.setAddress(updatedProfile.getAddress());

			return profileRepo.save(profile);
		});
	}

	// DELETE
	@Override
	public boolean deleteProfile(Long id) {
		if (profileRepo.existsById(id)) {
			profileRepo.deleteById(id);
			return true;
		}

		return false;
	}


}
