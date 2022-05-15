package com.demi.hdjassignment.repository;

import com.demi.hdjassignment.entity.Patient;

import java.util.List;

public interface PatientRepositoryCustom {

    List<Patient> findByMobile(Long hospitalId, String mobile);

    List<Patient> findAllByHospitalId(Long hospitalId);

}
