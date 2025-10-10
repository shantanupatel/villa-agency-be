package com.villa_agency.socialplatform.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.villa_agency.socialplatform.SocialPlatform;
import com.villa_agency.socialplatform.SocialPlatformRepository;
import com.villa_agency.socialplatform.SocialPlatformService;

@Service
public class SocialPlatformServiceImpl implements SocialPlatformService {

	private SocialPlatformRepository socialPlatformRepo;

	public SocialPlatformServiceImpl(SocialPlatformRepository socialPlatformRepo) {
		super();
		this.socialPlatformRepo = socialPlatformRepo;
	}

	@Override
	public void createSocialPlatform(SocialPlatform socialPlatform) {
		socialPlatformRepo.save(socialPlatform);
	}

	@Override
	public List<SocialPlatform> getSocialPlatforms() {
		return socialPlatformRepo.findAll();
	}

}
