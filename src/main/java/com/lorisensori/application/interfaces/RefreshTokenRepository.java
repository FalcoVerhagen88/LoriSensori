package com.lorisensori.application.interfaces;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lorisensori.application.domain.UserDevice;
import com.lorisensori.application.domain.token.RefreshToken;


public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

	@Override
	Optional<RefreshToken> findById(Long id);

	Optional<String> findTokenById(Long id);

	Optional<RefreshToken> findByToken(String token);

	Optional<UserDevice> findUserDeviceById(Long id);

	Optional<UserDevice> findUserDeviceByToken(String token);
}
