package com.villa_agency.socialplatform;

import java.util.List;

public interface SocialPlatformService {

	void createSocialPlatform(SocialPlatform socialPlatform);

	List<SocialPlatform> getSocialPlatforms();

}
