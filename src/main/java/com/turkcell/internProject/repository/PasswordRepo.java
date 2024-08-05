package com.turkcell.internProject.repository;

import com.turkcell.internProject.model.Password;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordRepo extends JpaRepository<Password, Long> {

}
