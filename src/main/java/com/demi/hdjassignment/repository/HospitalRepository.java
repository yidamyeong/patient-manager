package com.demi.hdjassignment.repository;

import com.demi.hdjassignment.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {

}