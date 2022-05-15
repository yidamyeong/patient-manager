package com.demi.hdjassignment.controller;

import com.demi.hdjassignment.controller.dto.CreateDto;
import com.demi.hdjassignment.controller.dto.DeleteDto;
import com.demi.hdjassignment.controller.dto.PatientDto;
import com.demi.hdjassignment.controller.dto.UpdateDto;
import com.demi.hdjassignment.entity.form.PatientCreateForm;
import com.demi.hdjassignment.entity.form.PatientIdForm;
import com.demi.hdjassignment.entity.form.PatientUpdateForm;
import com.demi.hdjassignment.entity.form.SearchForm;
import com.demi.hdjassignment.service.PatientService;
import com.demi.hdjassignment.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class PatientController {

    private final PatientService patientService;

    @PostMapping(value = "/patient")
    public CreateDto createPatient(@RequestHeader(value = "hospital-id") Long hospitalId,
                                   @Valid @RequestBody PatientCreateForm form) {
        ValidationUtil.rejectIfNotEqual(hospitalId, form.getHospitalId(), "Hospital ID");
        return new CreateDto(patientService.createPatient(form));
    }

    @PutMapping(value = "/patient")
    public UpdateDto updatePatient(@RequestHeader(value = "hospital-id") Long hospitalId,
                                   @Valid @RequestBody PatientUpdateForm form) {
        return patientService.updatePatient(hospitalId, form);
    }

    @DeleteMapping(value = "/patient")
    public DeleteDto deletePatient(@RequestHeader(value = "hospital-id") Long hospitalId,
                                   @Valid @RequestBody PatientIdForm form) {
        patientService.deletePatient(hospitalId, form.getPatientId());
        return new DeleteDto("DELETED");
    }

    @GetMapping(value = "/patient")
    public PatientDto getPatient(@RequestHeader(value = "hospital-id") Long hospitalId,
                                 @RequestParam("patient_id") Long patientId) {
        return patientService.findOne(hospitalId, patientId);
    }

    @GetMapping(value = "/patient/list")
    public List<PatientDto> getAllPatient(@RequestHeader(value = "hospital-id") Long hospitalId,
                                          @RequestParam(value = "name", required = false) String name,
                                          @RequestParam(value = "reg_id", required = false) String regId,
                                          @RequestParam(value = "birth", required = false) String birth,
                                          @RequestParam(value = "page_size", defaultValue = "10") Integer pageSize,
                                          @RequestParam(value = "page_no", defaultValue = "1") Integer pageNo) {

        return patientService.findAllBySearchCondition(new SearchForm(hospitalId, name, regId, birth, pageSize, pageNo));
    }

}