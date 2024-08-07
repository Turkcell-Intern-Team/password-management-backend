package com.turkcell.internProject.repository;

import com.turkcell.internProject.model.PasswordHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordHistoryRepo extends JpaRepository<PasswordHistory, Long> {
}
