package com.example.library_management_system.RequestDTOs;

import com.example.library_management_system.Entities.Student;
import com.example.library_management_system.Enum.Genre;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class GetStudentResponse {
    private Integer studentId;

    public GetStudentResponse(Student student) {
        this.name = student.getName();
        this.branch = student.getBranch();
        this.cgpa = student.getCgpa();
        this.phoneNo = student.getPhoneNo();
        if(student.getLibraryCard()!=null) {
            this.libraryCardId = student.getLibraryCard().getCardId();
        }
    }

    private String name;

    private String branch;

    private double cgpa;

    private String phoneNo;

    private int libraryCardId;

}
