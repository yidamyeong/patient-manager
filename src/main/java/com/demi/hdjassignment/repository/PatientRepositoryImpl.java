package com.demi.hdjassignment.repository;

import com.demi.hdjassignment.entity.Patient;
import com.demi.hdjassignment.entity.QPatient;
import com.demi.hdjassignment.entity.form.SearchForm;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class PatientRepositoryImpl implements PatientRepositoryCustom {

    private final EntityManager em;

    @Override
    public List<Patient> findByMobile(Long hospitalId, String mobile) {

        return em.createQuery(" select p "
                       +  " from Patient p "
                       +  " where p.hospital.id = : hospitalId "
                       +  " and p.mobile = : mobile "
                        , Patient.class)
                .setParameter("hospitalId", hospitalId)
                .setParameter("mobile", mobile)
                .getResultList();
    }

    @Override
    public List<Patient> findAllByHospitalId(Long hospitalId) {
        return em.createQuery(" select p "
                        +  " from Patient p "
                        +  " where p.hospital.id = : hospitalId "
                        , Patient.class)
                .setParameter("hospitalId", hospitalId)
                .getResultList();
    }

    @Override
    public List<Patient> findAllBySearchCondition(SearchForm form) {
        QPatient patient = QPatient.patient;

        JPAQueryFactory query = new JPAQueryFactory(em);
        return query
                .select(patient)
                .from(patient)
                .where(isHospitalIdEq(form.getHospitalId())
                        , isNameEq(form.getName())
                        , isRegIdEq(form.getRegId())
                        , isBirthEq(form.getBirth()))
                .offset((long) form.getPageSize() * (form.getPageNo() - 1))
                .limit(form.getPageSize())
                .fetch();
    }

    private BooleanExpression isHospitalIdEq(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        return QPatient.patient.hospital.id.eq(id);
    }

    private BooleanExpression isNameEq(String name) {
        if (!StringUtils.hasText(name)) {
            return null;
        }
        return QPatient.patient.name.eq(name);
    }

    private BooleanExpression isRegIdEq(String regId) {
        if (!StringUtils.hasText(regId)) {
            return null;
        }
        return QPatient.patient.regId.eq(regId);
    }

    private BooleanExpression isBirthEq(String birth) {
        if (!StringUtils.hasText(birth)) {
            return null;
        }
        return QPatient.patient.birth.eq(birth);
    }

}
