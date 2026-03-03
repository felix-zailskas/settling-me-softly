package io.github.felixzailskas.settlingmesoftly.repository;

import io.github.felixzailskas.settlingmesoftly.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {}
