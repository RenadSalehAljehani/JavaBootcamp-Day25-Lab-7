package com.example.learningmanagementsystem.Controller;

import com.example.learningmanagementsystem.ApiResponse.ApiResponse;
import com.example.learningmanagementsystem.Model.Teacher;
import com.example.learningmanagementsystem.Service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/teacher")
@RequiredArgsConstructor
public class TeacherController {

    // 1. Create a variable of type TeacherService
    private final TeacherService teacherService;

    // 2. Create CRUD endPoints
    // 2.1 Post(create)
    @PostMapping("/addTeacher")
    public ResponseEntity addTeacher(@RequestBody @Valid Teacher teacher, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        teacherService.addTeacher(teacher);
        return ResponseEntity.status(200).body(new ApiResponse("Teacher Added."));
    }

    // 2.2 Get(read)
    @GetMapping("/getTeachers")
    public ArrayList<Teacher> getTeachers() {
        return teacherService.getTeachers();
    }

    // 2.3 Put(update)
    @PutMapping("/updateTeacher/{ID}")
    public ResponseEntity updateTeacher(@PathVariable String ID, @RequestBody @Valid Teacher teacher, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        if (teacherService.updateTeacher(ID, teacher)) {
            return ResponseEntity.status(200).body(new ApiResponse("Teacher Updated."));
        }
        return ResponseEntity.status(400).body("Teacher ID Not Found.");
    }

    // 2.4 Delete
    @DeleteMapping("/deleteTeacher/{ID}")
    public ResponseEntity deleteTeacher(@PathVariable String ID) {
        if (teacherService.deleteTeacher(ID)) {
            return ResponseEntity.status(200).body(new ApiResponse("Teacher Deleted."));
        }
        return ResponseEntity.status(400).body("Teacher ID Not Found.");
    }

    // 3. Extra endPoints
    // 3.1 teachersWithSpecificExperience method
    @GetMapping("/searchByExperience/{experience}")
    public ResponseEntity searchByExperience(@PathVariable int experience) {
        if (teacherService.searchByExperience(experience) != null) {
            return ResponseEntity.status(200).body(teacherService.searchByExperience(experience));
        }
        return ResponseEntity.status(400).body("There Are No Teachers with " + experience + " Years of Experience.");
    }

    // 3.2 teachersExceededAllowedNumOfCourses method
    @GetMapping("/teachersExceededAllowedNumOfCourses")
    public ResponseEntity teachersExceededAllowedNumOfCourses() {
        if (teacherService.teachersExceededAllowedNumOfCourses() != null) {
            return ResponseEntity.status(200).body(teacherService.teachersExceededAllowedNumOfCourses());
        }
        return ResponseEntity.status(400).body(new ApiResponse("There Are No Teachers Exceeded Allowed Number of Courses."));
    }

    // 3.3 rewardTeachers method
    @PutMapping("/rewardTeachers")
    public ResponseEntity rewardTeachers() {
        if (teacherService.rewardTeachers()) {
            return ResponseEntity.status(200).body(new ApiResponse("Teachers Rewarded."));
        }
        return ResponseEntity.status(400).body(new ApiResponse("No Teacher Has Rewarded. There Are No Teachers With more than 5 years of experience."));
    }
}