package com.demi.hdjassignment.service;

import com.demi.hdjassignment.controller.dto.VisitDto;
import com.demi.hdjassignment.entity.Patient;
import com.demi.hdjassignment.entity.Visit;
import com.demi.hdjassignment.entity.code.VisitStatus;
import com.demi.hdjassignment.repository.PatientRepository;
import com.demi.hdjassignment.repository.VisitRepository;
import com.demi.hdjassignment.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.InvalidParameterException;

@Slf4j
@Service
@RequiredArgsConstructor
public class VisitService {

    private final PatientRepository patientRepository;
    private final VisitRepository visitRepository;

    @Transactional
    public VisitDto visit(Long hospitalId, Long patientId) {

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new InvalidParameterException("Invalid Patient ID"));

        ValidationUtil.rejectIfNotEqual(hospitalId, patient.getHospital().getId(), "Hospital ID");

        Visit visit = Visit.builder()
                .hospital(patient.getHospital())
                .patient(patient)
                .build();

        visitRepository.save(visit);
        log.debug("visit = {}", visit);

        return new VisitDto(visit);
    }

    @Transactional
    public VisitDto updateStatus(Long hospitalId, Long patientId, String visitCode) {

        Visit visit = visitRepository.findRecentVisitByPatientId(patientId);
        ValidationUtil.rejectIfNull(visit, "Patient ID");
        ValidationUtil.rejectIfNotEqual(hospitalId, visit.getHospital().getId(), "Hospital ID");

        visit.updateStatus(visitCode);
        log.debug("visit = {}", visit);

        return new VisitDto(visit);
    }

}
