package com.demi.hdjassignment.entity;

import com.demi.hdjassignment.entity.code.Gender;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(
        name = "patient_seq_generator",
        sequenceName = "patient_seq"
)
public class Patient {

    @Id
    @Column(name = "patient_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patient_seq_generator")
    private Long id;                // 환자아이디

    @ToString.Exclude
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;      // 병원아이디

    @Column(length = 45)
    private String name;            // 환자명

    @Column(length = 13)
    private String regId;           // 환자등록번호

    @Column(length = 10)
    @Enumerated(EnumType.STRING)
    private Gender gender;          // 성별코드

    @Column(length = 10)
    private String birth;           // 생년월일

    @Column(length = 20)
    private String mobile;          // 휴대전화번호

    @ToString.Exclude
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Visit> visits = new ArrayList<>();

    @Builder
    public Patient(Hospital hospital, String name, String genderCode, String birth, String mobile) {
        this.hospital = hospital;
        this.name = name;
        this.regId = hospital.createNewRegId();
        this.gender = Gender.findByCode(genderCode);
        this.birth = birth;
        this.mobile = mobile;
        this.hospital.addPatient(this);
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public void updatePatient(String name, String genderCode, String birth, String mobile, Visit... visits) {
        this.name = name;
        this.gender = Gender.findByCode(genderCode);
        this.birth = birth;
        this.mobile = mobile;
        Arrays.stream(visits).forEach(this::addVisit);
    }

    // 방문 기록 추가
    public void addVisit(Visit visit) {
        this.visits.add(visit);
        visit.setPatient(this);
    }

}