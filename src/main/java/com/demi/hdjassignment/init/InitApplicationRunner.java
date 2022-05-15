package com.demi.hdjassignment.init;

import com.demi.hdjassignment.entity.Hospital;
import com.demi.hdjassignment.entity.Patient;
import com.demi.hdjassignment.entity.Visit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class InitApplicationRunner implements ApplicationRunner {

    private final InitService initService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        initService.initDatabase();
    }

}

@Component
@Transactional
@RequiredArgsConstructor
class InitService {

    private final EntityManager entityManager;

    public void initDatabase() throws InterruptedException {
        List<Hospital> hospitals = initHospital();
        List<Patient> patients = initPatient(hospitals);
        initVisit(patients);

    }

    private List<Hospital> initHospital() {
        for (int i = 0; i < 10; i++) {
            Hospital hospital = Hospital.builder()
                            .name("Hospital Name-" + (i + 1))
                            .instId(UUID.randomUUID().toString().substring(0, 8).toUpperCase())
                            .director("D-" + (i + 1))
                            .build();
            entityManager.persist(hospital);
        }

        return entityManager
                .createQuery(
                        " select h from Hospital h" +
                           " order by h.id asc", Hospital.class)
                .getResultList();
    }

    private List<Patient> initPatient(List<Hospital> hospitals) {
        for (Hospital hospital : hospitals) {
            for (int i = 0; i < 20; i++) {
                Patient patient = Patient.builder()
                        .hospital(hospital)
                        .name("Patient-" + (i + 1))
                        .genderCode(i < 5 ? "F" : "M")
                        .birth("yyyy-MM-dd")
                        .mobile("010-1234-56" + (i < 10 ? "0" : "") + i)
                        .build();
                entityManager.persist(patient);
            }
        }

        return entityManager
                .createQuery(
                        " select p from Patient p " +
                           " order by p.id asc ", Patient.class)
                .getResultList();
    }

    private void initVisit(List<Patient> patients) throws InterruptedException {
        for (Patient patient : patients) {
            for (int i = 0; i < 3; i++) {
                Visit visit = Visit.builder()
                        .hospital(patient.getHospital())
                        .patient(patient)
                        .build();
                if (i == 0) {
                    visit.updateStatus("2");
                } else if (i == 1) {
                    visit.updateStatus("3");
                }
                entityManager.persist(visit);
                Thread.sleep(1);
            }
        }
    }

}
