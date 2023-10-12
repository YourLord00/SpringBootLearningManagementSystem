package com.example.studentmanagement.model;

import jakarta.persistence.*;

@Entity
@Table(name="student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "university_class_id")
    private UniversityClass universityClass;

    public Student(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Student() {
    }

    public UniversityClass getUniversityClass() {
        return universityClass;
    }

    public void setUniversityClass(UniversityClass universityClass) {
        this.universityClass = universityClass;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        String str = "";
        str += "Primary ID: " + getId();
        str += "Name: " + getName();
        str += "UniversityClass: " + getUniversityClass();
        return str;
    }
}
