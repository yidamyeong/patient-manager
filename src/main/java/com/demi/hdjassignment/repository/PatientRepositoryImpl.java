package com.demi.hdjassignment.repository;

import com.demi.hdjassignment.entity.Patient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PatientRepositoryImpl implements PatientRepositoryCustom {

    private final EntityManager em;

    @Override
    public List<Patient> findByMobile(Long hospitalId, String mobile) {

        return em.createQuery(" select p "
                       +  " from Patient p "
                       +  " where p.hospital.id = : hospitalId "
                       +  " and p.mobile = : mobile ", Patient.class)
                .setParameter("hospitalId", hospitalId)
                .setParameter("mobile", mobile)
                .getResultList();
    }

}
