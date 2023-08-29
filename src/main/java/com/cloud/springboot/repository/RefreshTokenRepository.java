package com.cloud.springboot.repository;

import com.cloud.springboot.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByUserId(Long Id);
    Optional<RefreshToken> findByRefreshToken(String refreshToken);
}
