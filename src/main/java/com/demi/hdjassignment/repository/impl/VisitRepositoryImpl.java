package com.demi.hdjassignment.repository.impl;

import com.demi.hdjassignment.entity.Visit;
import com.demi.hdjassignment.entity.code.VisitStatus;
import com.demi.hdjassignment.repository.VisitRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class VisitRepositoryImpl implements VisitRepositoryCustom {

    private final EntityManager em;

    @Override
    public Visit findRecentVisitByPatientId(Long patientId) {
        return em.createQuery(" select v "
                               + " from Visit v "
                               + " where v.patient.id = : patientId "
                               + " and v.status = : status "
                               + " order by v.createdAt desc "
                               , Visit.class)
                .setParameter("patientId", patientId)
                .setParameter("status", VisitStatus.VISITING)
                .getSingleResult();
    }

}
