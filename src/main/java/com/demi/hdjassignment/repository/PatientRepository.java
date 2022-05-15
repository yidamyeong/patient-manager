package com.demi.hdjassignment.repository;

import com.demi.hdjassignment.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}