package com.villa_agency.sociallink.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.villa_agency.sociallink.SocialLink;
import com.villa_agency.sociallink.SocialLinkRepository;
import com.villa_agency.sociallink.SocialLinkService;

@Service
public class SocialLinkServiceImpl implements SocialLinkService {

	private SocialLinkRepository socialLinkRepo;

	public SocialLinkServiceImpl(SocialLinkRepository socialLinkRepo) {
		super();
		this.socialLinkRepo = socialLinkRepo;
	}

	@Override
	public void createSocialLink(SocialLink socialLink) {
		// socialLinkRepo.save(socialLink);
		Long platformId = socialLink.getSocialPlatform() != null ? socialLink.getSocialPlatform().getId() : null;

		if (platformId == null) {
			throw new RuntimeException("SocialPlatform ID is required");
		}

		// No need to fetch full entity; JPA will link via id
		socialLinkRepo.save(socialLink);
	}

	@Override
	public List<SocialLink> getSocialLinks() {
		// TODO Auto-generated method stub
		return socialLinkRepo.findAll();
	}

}
