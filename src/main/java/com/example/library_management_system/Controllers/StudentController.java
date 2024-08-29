package com.example.library_management_system.Controllers;


import com.example.library_management_system.Entities.Student;
import com.example.library_management_system.RequestDTOs.AddStudentRequest;
import com.example.library_management_system.RequestDTOs.GetStudentResponse;
import com.example.library_management_system.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/addStudent")
    public ResponseEntity<String> addStudent(@RequestBody AddStudentRequest addStudentRequest){
        String result = studentService.addStudent(addStudentRequest);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("findById")
    public ResponseEntity findStudentById(@RequestParam int studentId){
        try {
            Student student = studentService.findStudentById(studentId);
            GetStudentResponse getStudentResponse = new GetStudentResponse(student);
            return new ResponseEntity(getStudentResponse, HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
}
