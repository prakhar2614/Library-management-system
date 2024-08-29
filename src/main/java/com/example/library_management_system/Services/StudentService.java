package com.example.library_management_system.Services;

import com.example.library_management_system.Entities.Student;
import com.example.library_management_system.Repositories.StudentRepository;
import com.example.library_management_system.RequestDTOs.AddStudentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public String addStudent(AddStudentRequest addStudentRequest){
        Student student = new Student();
        student.setBranch(addStudentRequest.getBranch());
        student.setCgpa(addStudentRequest.getCgpa());
        student.setName(addStudentRequest.getName());
        student.setPhoneNo(addStudentRequest.getPhoneNo());
        Student s = studentRepository.save(student);
        return "The student has been saved in DB with student id: "+ s.getStudentId();
    }

    public Student findStudentById(int studentId) throws Exception{
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if(optionalStudent.isEmpty()){
            throw new Exception("Student ID is incorrect");
        }
        return optionalStudent.get();
    }
}
