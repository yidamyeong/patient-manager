package com.demi.hdjassignment.entity;

import com.demi.hdjassignment.entity.code.VisitStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(
        name = "visit_seq_generator",
        sequenceName = "visit_seq"
)
public class Visit {

    @Id
    @Column(name = "visit_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "visit_seq_generator")
    private Long id;                    // 환자방문아이디

    @ToString.Exclude
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;          // 병원 아이디

    @ToString.Exclude
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;            // 환자 아이디

    private LocalDateTime createdAt;    // 접수 일시

    @Column(length = 10)
    @Enumerated(EnumType.STRING)
    private VisitStatus status;         // 방문상태코드

    @Builder
    public Visit(Hospital hospital, Patient patient) {
        this.hospital = hospital;
        setPatient(patient);
        createdAt = LocalDateTime.now();
        status = VisitStatus.VISITING;
        patient.addVisit(this);
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
        patient.getVisits().add(this);
    }

}