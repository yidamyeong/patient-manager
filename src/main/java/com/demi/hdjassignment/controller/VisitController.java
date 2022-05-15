package com.demi.hdjassignment.controller;

import com.demi.hdjassignment.controller.dto.VisitDto;
import com.demi.hdjassignment.entity.form.PatientIdForm;
import com.demi.hdjassignment.entity.form.VisitUpdateForm;
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
    public VisitDto createVisit(@RequestHeader(value = "hospital-id") Long hospitalId,
                                @Valid @RequestBody PatientIdForm form) {
        return visitService.visit(hospitalId, form.getPatientId());
    }

    @PutMapping(value = "/visit")
    public VisitDto updateStatus(@RequestHeader(value = "hospital-id") Long hospitalId,
                                 @Valid @RequestBody VisitUpdateForm form) {
        return visitService.updateStatus(hospitalId, form.getPatientId(), form.getVisitCode());
    }


}