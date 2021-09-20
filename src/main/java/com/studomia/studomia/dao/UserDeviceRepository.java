package com.studomia.studomia.dao;


import com.studomia.studomia.model.UserDevice;
import com.studomia.studomia.model.token.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDeviceRepository extends JpaRepository<UserDevice, Long> {

    @Override
    Optional<UserDevice> findById(Long id);

    Optional<UserDevice> findByRefreshToken(RefreshToken refreshToken);

 //   Optional<UserDevice> findByUserId(Long userId);
}
