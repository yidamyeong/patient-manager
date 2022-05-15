package com.demi.hdjassignment.service;

import com.demi.hdjassignment.controller.dto.UpdateDto;
import com.demi.hdjassignment.entity.Hospital;
import com.demi.hdjassignment.entity.Patient;
import com.demi.hdjassignment.entity.form.PatientCreateForm;
import com.demi.hdjassignment.entity.form.PatientUpdateForm;
import com.demi.hdjassignment.repository.HospitalRepository;
import com.demi.hdjassignment.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.InvalidParameterException;

@Slf4j
@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final HospitalRepository hospitalRepository;

    @Transactional
    public Long createPatient(PatientCreateForm form) {

        Hospital hospital = hospitalRepository.findById(form.getHospitalId())
                .orElseThrow(() -> new InvalidParameterException("Invalid Hospital ID"));

        Patient patient = Patient.builder()
                .hospital(hospital)
                .name(form.getName())
                .genderCode(form.getGender())
                .birth(form.getBirth())
                .mobile(form.getMobile())
                .build();

        patientRepository.save(patient);

        return patient.getId();
    }

    @Transactional
    public UpdateDto updatePatient(PatientUpdateForm form) {

        Patient patient = patientRepository.findById(form.getPatientId())
                .orElseThrow(() -> new InvalidParameterException("Invalid Patient ID"));

        patient.updatePatient(form.getName(), form.getGender(), form.getBirth(), form.getMobile());

        return new UpdateDto(patient);
    }

}