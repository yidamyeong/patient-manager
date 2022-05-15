package com.demi.hdjassignment.service;

import com.demi.hdjassignment.controller.dto.PatientDto;
import com.demi.hdjassignment.controller.dto.PatientListDto;
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

    // 환자 등록
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

    // 휴대폰 번호 중복 체크
    private void rejectIfMobileDuplicated(Long hospitalId, String mobile) {
        List<Patient> patients = patientRepository.findByMobile(hospitalId, mobile);
        log.debug("patient = {}", patients);
        if (!patients.isEmpty()) {
            throw new IllegalStateException("Mobile number already exists");
        }
    }

    // 환자 정보 수정
    @Transactional
    public UpdateDto updatePatient(Long hospitalId, PatientUpdateForm form) {

        Patient patient = patientRepository.findById(form.getPatientId())
                .orElseThrow(() -> new InvalidParameterException("Invalid Patient ID"));

        ValidationUtil.rejectIfNotEqual(hospitalId, patient.getHospital().getId(), "Hospital ID");

        patient.updatePatient(form.getName(), form.getGender(), form.getBirth(), form.getMobile());

        return new UpdateDto(patient);
    }

    // 환자 삭제
    @Transactional
    public void deletePatient(Long hospitalId, Long patientId) {

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new InvalidParameterException("Invalid Patient ID"));

        ValidationUtil.rejectIfNotEqual(hospitalId, patient.getHospital().getId(), "Hospital ID");

        patientRepository.delete(patient);
    }

    // 한 명의 환자 조회
    @Transactional
    public PatientDto findOne(Long hospitalId, Long patientId) {

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new InvalidParameterException("Invalid Patient ID"));

        ValidationUtil.rejectIfNotEqual(hospitalId, patient.getHospital().getId(), "Hospital ID");

        PatientDto dto = new PatientDto(patient);
        log.debug("dto = {}", dto);

        return dto;
    }

    // 환자 목록으로 조회
    @Transactional
    public List<PatientListDto> findAllBySearchCondition(SearchForm form) {

        if (form.getPageNo() < 1 || form.getPageSize() > 10) {
            throw new IllegalStateException("Not a proper value for page_size or page_no");
        }

        List<Patient> patients = patientRepository.findAllBySearchCondition(form);
        log.debug("patients = {}", patients);

        return patients.stream()
                .map(PatientListDto::new)
                .collect(Collectors.toList());
    }

}