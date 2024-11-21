package com.example.learningmanagementsystem.Service;

import com.example.learningmanagementsystem.Model.Course;
import com.example.learningmanagementsystem.Model.Student;
import com.example.learningmanagementsystem.Model.Teacher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CourseService {

    // 1. Create a list of courses
    ArrayList<Course> courses = new ArrayList<>();

    // 2. Create CRUD endPoints
    // 2.1 Post(create)
    public void addCourse(Course course) {
        courses.add(course);
    }

    // 2.2 Get(read)
    public ArrayList<Course> getCourses() {
        return courses;
    }

    // 2.3 Put(update)
    public boolean updateCourse(String ID, Course course) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getID().equalsIgnoreCase(ID)) {
                courses.set(i, course);
                return true;
            }
        }
        return false;
    }

    // 2.4 Delete
    public boolean deleteCourse(String ID) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getID().equalsIgnoreCase(ID)) {
                courses.remove(i);
                return true;
            }
        }
        return false;
    }

    // 3. Extra endPoints
    // 3.1 Method to set course as full
    public boolean setCourseToFull(String ID) {
        for (Course course : courses) {
            if (course.getID().equalsIgnoreCase(ID)) {
                course.setFull(true);
                return true;
            }
        }
        return false;
    }

    // 3.2 Method to search for a course by title
    public Course searchByTitle(String title) {
        for (Course course : courses) {
            if (course.getTitle().equalsIgnoreCase(title)) {
                return course;
            }
        }
        return null;
    }

    // 3.3 Method to get courses by a rating range
    public ArrayList<Course> getCoursesByRating(double minR, double maxR) {
        ArrayList<Course> coursesInRangeRating = new ArrayList<>();
        for (Course course : courses) {
            if (course.getRating() >= minR && course.getRating() <= maxR) {
                coursesInRangeRating.add(course);
            }
        }
        if (coursesInRangeRating.isEmpty()) {
            return null;
        }
        return coursesInRangeRating;
    }
}