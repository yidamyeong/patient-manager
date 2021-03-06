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

    /**
     * [6] 환자 방문시 접수하기
     * https://2dam0.notion.site/6-API-0ddbdd548973496a9502ad2df9f547ee
     */
    @PostMapping(value = "/visit")
    public VisitDto createVisit(@RequestHeader(value = "hospital-id") Long hospitalId,
                                @Valid @RequestBody PatientIdForm form) {
        return visitService.visit(hospitalId, form.getPatientId());
    }

    /**
     * [7] 환자 방문 정보 변경 - 접수 취소, 접수 종료
     * https://2dam0.notion.site/7-API-4993dfa8dceb4d3f8fb80cceb27dff62
     */
    @PutMapping(value = "/visit")
    public VisitDto updateStatus(@RequestHeader(value = "hospital-id") Long hospitalId,
                                 @Valid @RequestBody VisitUpdateForm form) {
        return visitService.updateStatus(hospitalId, form.getPatientId(), form.getVisitCode());
    }

}