package com.demi.hdjassignment.controller;

import com.demi.hdjassignment.controller.dto.VisitDto;
import com.demi.hdjassignment.entity.form.PatientIdForm;
import com.demi.hdjassignment.service.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class VisitController {

    private final VisitService visitService;

    @PostMapping(value = "/visit")
    public VisitDto createVisit(@Valid @RequestBody PatientIdForm form) {
        return visitService.visit(form.getPatientId());
    }

}