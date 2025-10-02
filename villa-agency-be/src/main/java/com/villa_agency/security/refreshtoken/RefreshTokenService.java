package com.villa_agency.security.refreshtoken;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.villa_agency.exception.TokenRefreshException;
import com.villa_agency.user.UserRepository;

@Service
public class RefreshTokenService {

	@Value("${villaagency.app.jwtRefreshExpirationMs}")
	private Long refreshTokenDurationMs;

	private RefreshTokenRepository refreshTokenRepository;

	private UserRepository userRepository;

	public RefreshTokenService(RefreshTokenRepository refreshTokenRepository,
			UserRepository userRepository) {
		super();
		this.refreshTokenRepository = refreshTokenRepository;
		this.userRepository = userRepository;
	}

	public Optional<RefreshToken> findByToken(String token) {
		return refreshTokenRepository.findByToken(token);
	}

	public RefreshToken createRefreshToken(Long userId) {
		RefreshToken refreshToken = new RefreshToken();

		refreshToken.setUser(userRepository.findById(userId).get());
		refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
		refreshToken.setToken(UUID.randomUUID().toString());

		refreshToken = refreshTokenRepository.save(refreshToken);

		return refreshToken;
	}

	public RefreshToken verifyExpiration(RefreshToken token) {
    if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
      refreshTokenRepository.delete(token);
      throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
    }

    return token;
  }

	@Transactional
  public int deleteByUserId(Long userId) {
    return refreshTokenRepository.deleteByUser(userRepository.findById(userId).get());
  }
}
