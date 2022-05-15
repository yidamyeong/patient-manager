package com.demi.hdjassignment.controller;

import com.demi.hdjassignment.controller.dto.CreateDto;
import com.demi.hdjassignment.entity.form.PatientCreateForm;
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

}