package com.demi.hdjassignment.service;

import com.demi.hdjassignment.controller.dto.PatientDto;
import com.demi.hdjassignment.controller.dto.UpdateDto;
import com.demi.hdjassignment.entity.Hospital;
import com.demi.hdjassignment.entity.Patient;
import com.demi.hdjassignment.entity.form.PatientCreateForm;
import com.demi.hdjassignment.entity.form.PatientUpdateForm;
import com.demi.hdjassignment.entity.form.SearchForm;
import com.demi.hdjassignment.repository.HospitalRepository;
import com.demi.hdjassignment.repository.PatientRepository;
import com.demi.hdjassignment.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.stream.Collectors;

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

        rejectIfMobileDuplicated(form.getHospitalId(), form.getMobile());
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

    private void rejectIfMobileDuplicated(Long hospitalId, String mobile) {
        List<Patient> patients = patientRepository.findByMobile(hospitalId, mobile);
        log.debug("patient = {}", patients);
        if (!patients.isEmpty()) {
            throw new IllegalStateException("Mobile number already exists");
        }
    }

    @Transactional
    public UpdateDto updatePatient(Long hospitalId, PatientUpdateForm form) {

        Patient patient = patientRepository.findById(form.getPatientId())
                .orElseThrow(() -> new InvalidParameterException("Invalid Patient ID"));

        ValidationUtil.rejectIfNotEqual(hospitalId, patient.getHospital().getId(), "Hospital ID");

        patient.updatePatient(form.getName(), form.getGender(), form.getBirth(), form.getMobile());

        return new UpdateDto(patient);
    }

    @Transactional
    public void deletePatient(Long hospitalId, Long patientId) {

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new InvalidParameterException("Invalid Patient ID"));

        ValidationUtil.rejectIfNotEqual(hospitalId, patient.getHospital().getId(), "Hospital ID");

        patientRepository.delete(patient);
    }

    @Transactional
    public PatientDto findOne(Long hospitalId, Long patientId) {

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new InvalidParameterException("Invalid Patient ID"));

        ValidationUtil.rejectIfNotEqual(hospitalId, patient.getHospital().getId(), "Hospital ID");

        PatientDto dto = new PatientDto(patient);
        log.debug("dto = {}", dto);

        return dto;
    }

    @Transactional
    public List<PatientDto> findAllBySearchCondition(SearchForm form) {

        if (form.getPageNo() < 1 || form.getPageSize() > 10) {
            throw new IllegalStateException("Not a proper value for page_size or page_no");
        }

        List<Patient> patients = patientRepository.findAllBySearchCondition(form);
        log.debug("patients = {}", patients);

        return patients.stream()
                .map(PatientDto::new)
                .collect(Collectors.toList())
                ;
    }

}