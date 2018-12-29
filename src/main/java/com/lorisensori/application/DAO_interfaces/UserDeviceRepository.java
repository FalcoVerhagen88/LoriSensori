package com.lorisensori.application.DAO_interfaces;

import com.lorisensori.application.domain.UserDevice;
import com.lorisensori.application.domain.token.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;



public interface UserDeviceRepository extends JpaRepository<UserDevice, Long> {

	@Override
	Optional<UserDevice> findById(Long id);

	Optional<RefreshToken> findRefreshTokenById(Long id);

	Optional<UserDevice> findByRefreshToken(RefreshToken refreshToken);

	Optional<UserDevice> findByDeviceId(String deviceId);
}
