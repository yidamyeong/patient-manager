package com.demi.hdjassignment.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(
        name = "hospital_seq_generator",
        sequenceName = "hospital_seq"
)
public class Hospital {

    @Id
    @Column(name = "hospital_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hospital_seq_generator")
    private Long id;            // 병원아이디

    @Column(length = 45)
    private String name;        // 병원명

    @Column(unique = true, length = 20)
    private String instId;      // 요양기관번호

    @Column(length = 10)
    private String director;    // 병원장명

    @ToString.Exclude
    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL)
    private List<Patient> patients = new ArrayList<>();

    @Builder
    public Hospital(String name, String instId, String director) {
        this.name = name;
        this.instId = instId;
        this.director = director;
    }

    // 병원별 고유한 환자등록번호 만들기
    public String createNewRegId() {
        return instId + "-" + (patients.size() + 1);
    }

    // 환자 목록 추가
    public void addPatient(Patient patient) {
        patients.add(patient);
        patient.setHospital(this);
    }

}