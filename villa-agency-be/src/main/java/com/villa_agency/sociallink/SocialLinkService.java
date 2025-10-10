package com.villa_agency.sociallink;

import java.util.List;

public interface SocialLinkService {

	void createSocialLink(SocialLink socialLink);

	List<SocialLink> getSocialLinks();

}
