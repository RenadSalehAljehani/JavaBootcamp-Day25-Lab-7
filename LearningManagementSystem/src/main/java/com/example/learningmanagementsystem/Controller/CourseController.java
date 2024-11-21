package com.example.learningmanagementsystem.Controller;

import com.example.learningmanagementsystem.ApiResponse.ApiResponse;
import com.example.learningmanagementsystem.Model.Course;
import com.example.learningmanagementsystem.Service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/course")
@RequiredArgsConstructor
public class CourseController {

    // 1. Create a variable of type CourseService
    private final CourseService courseService;

    // 2. Create CRUD endPoints
    // 2.1 Post(create)
    @PostMapping("/addCourse")
    public ResponseEntity addCourse(@RequestBody @Valid Course course, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        courseService.addCourse(course);
        return ResponseEntity.status(200).body(new ApiResponse("Course Added."));
    }

    // 2.2 Get(read)
    @GetMapping("/getCourses")
    public ArrayList<Course> getCourses() {
        return courseService.getCourses();
    }

    // 2.3 Put(update)
    @PutMapping("/updateCourse/{ID}")
    public ResponseEntity updateCourse(@PathVariable String ID, @RequestBody @Valid Course course, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        if (courseService.updateCourse(ID, course)) {
            return ResponseEntity.status(200).body(new ApiResponse("Course Updated."));
        }
        return ResponseEntity.status(400).body("Course ID Not Found.");
    }

    // 2.4 Delete
    @DeleteMapping("/deleteCourse/{ID}")
    public ResponseEntity deleteCourse(@PathVariable String ID) {
        if (courseService.deleteCourse(ID)) {
            return ResponseEntity.status(200).body(new ApiResponse("Course Deleted."));
        }
        return ResponseEntity.status(400).body("Course ID Not Found.");
    }

    // 3. Extra endPoints
    // 3.1 checkIfCourseIsFull method
    @PutMapping("/setCourseToFull/{ID}")
    public ResponseEntity setCourseToFull(@PathVariable String ID){
        if(courseService.setCourseToFull(ID)){
            return ResponseEntity.status(200).body(new ApiResponse("Course Set To Full."));
        }
        return ResponseEntity.status(400).body("Course ID Not Found.");
    }

    // 3.2 searchByTitle method
    @GetMapping("/searchByTitle/{title}")
    public ResponseEntity searchByTitle(@PathVariable String title){
        if(courseService.searchByTitle(title) != null){
            return ResponseEntity.status(200).body(courseService.searchByTitle(title));
        }
        return ResponseEntity.status(400).body("There Is No Course With Title (" + title + ").");
    }

    // 3.3 getCoursesByRating method
    @GetMapping("/getCoursesByRating/{minR}/{maxR}")
    public ResponseEntity getCoursesByRating(@PathVariable double minR, @PathVariable double maxR){
        if(courseService.getCoursesByRating(minR,maxR) != null){
            return ResponseEntity.status(200).body(courseService.getCoursesByRating(minR,maxR));
        }
        return ResponseEntity.status(400).body("There Are No Courses With Rating Between " + minR + "-" + maxR);
    }
}