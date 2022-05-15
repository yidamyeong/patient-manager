package com.demi.hdjassignment.repository;

import com.demi.hdjassignment.entity.Visit;

public interface VisitRepositoryCustom {

    Visit findRecentVisitByPatientId(Long patientId);

}
