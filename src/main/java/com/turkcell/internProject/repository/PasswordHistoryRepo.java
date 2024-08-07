package com.turkcell.internProject.repository;

import com.turkcell.internProject.model.PasswordHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasswordHistoryRepo extends JpaRepository<PasswordHistory, Long> {

    // En son kaydedilen geçmiş kaydını al
    Optional<PasswordHistory> findTopByPasswordIdOrderByProcessDateDesc(Long passwordId);
}
