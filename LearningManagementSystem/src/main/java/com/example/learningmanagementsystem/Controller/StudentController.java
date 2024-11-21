package com.example.learningmanagementsystem.Controller;

import com.example.learningmanagementsystem.ApiResponse.ApiResponse;
import com.example.learningmanagementsystem.Model.Student;
import com.example.learningmanagementsystem.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {

    // 1. Create a variable of type StudentService
    private final StudentService studentService;

    // 2. Create CRUD endPoints
    // 2.1 Post(create)
    @PostMapping("/addStudent")
    public ResponseEntity addStudent(@RequestBody @Valid Student student, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        studentService.addStudent(student);
        return ResponseEntity.status(200).body(new ApiResponse("Student Added."));
    }

    // 2.2 Get(read)
    @GetMapping("/getStudents")
    public ArrayList<Student> getStudents(){
        return studentService.getStudents();
    }

    // 2.3 Put(update)
    @PutMapping("/updateStudent/{ID}")
    public ResponseEntity updateStudent(@PathVariable String ID, @RequestBody @Valid Student student, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        if(studentService.updateStudent(ID,student)){
            return ResponseEntity.status(200).body(new ApiResponse("Student Updated."));
        }
        return ResponseEntity.status(400).body("Student ID Not Found.");
    }

    // 2.4 Delete
    @DeleteMapping("/deleteStudent/{ID}")
    public ResponseEntity deleteStudent(@PathVariable String ID){
        if(studentService.deleteStudent(ID)){
            return ResponseEntity.status(200).body(new ApiResponse("Student Deleted."));
        }
        return ResponseEntity.status(400).body("Student ID Not Found.");
    }

    // 3. Extra endPoints
    // 3.1 getGpaAverage method
    @GetMapping("/getGpaAverage")
    public ResponseEntity getGpaAverage(){
        return ResponseEntity.status(200).body(studentService.getGpaAverage());
    }

    // 3.2 rewardStudents method
    @PutMapping("/rewardStudents")
    public ResponseEntity rewardStudents(){
        if(studentService.rewardStudents()){
            return ResponseEntity.status(200).body(new ApiResponse("Students Rewarded."));
        }
        return ResponseEntity.status(400).body("There Are No Students Met the Conditions.");
    }

    // 3.3 exceededAllowedAbsenceDays method
    @GetMapping("/exceededAllowedAbsenceDays")
    public ResponseEntity exceededAllowedAbsenceDays(){
        if(studentService.exceededAllowedAbsenceDays() != null){
            return ResponseEntity.status(200).body(studentService.exceededAllowedAbsenceDays());
        }
        return ResponseEntity.status(400).body("There Are No Students Has Exceeded Allowed Absence Days.");
    }
}