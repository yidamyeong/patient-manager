package com.demi.hdjassignment.controller;

import com.demi.hdjassignment.controller.dto.CreateDto;
import com.demi.hdjassignment.controller.dto.DeleteDto;
import com.demi.hdjassignment.controller.dto.UpdateDto;
import com.demi.hdjassignment.entity.form.PatientCreateForm;
import com.demi.hdjassignment.entity.form.PatientUpdateForm;
import com.demi.hdjassignment.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class PatientController {

    private final PatientService patientService;

    @PostMapping(value = "/patient")
    public CreateDto createPatient(@Valid @RequestBody PatientCreateForm form) {
        return new CreateDto(patientService.createPatient(form));
    }

    @PutMapping(value = "/patient")
    public UpdateDto updatePatient(@Valid @RequestBody PatientUpdateForm form) {
        return patientService.updatePatient(form);
    }

    @DeleteMapping(value = "/patient")
    public DeleteDto deletePatient(@Valid @RequestParam("patient_id") Long patientId) {
        patientService.deletePatient(patientId);
        return new DeleteDto("DELETED");
    }
}