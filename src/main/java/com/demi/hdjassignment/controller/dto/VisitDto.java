package com.demi.hdjassignment.controller.dto;

import com.demi.hdjassignment.entity.Visit;
import com.demi.hdjassignment.entity.code.VisitStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class VisitDto {

    @JsonProperty("visit_id")
    private Long id;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("visit_status")
    private VisitStatus status;

    public VisitDto(Visit visit) {
        id = visit.getId();
        createdAt = visit.getCreatedAt();
        status = visit.getStatus();
    }
}
