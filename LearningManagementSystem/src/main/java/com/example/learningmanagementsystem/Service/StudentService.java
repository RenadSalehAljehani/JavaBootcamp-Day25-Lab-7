package com.example.learningmanagementsystem.Service;

import com.example.learningmanagementsystem.Model.Student;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class StudentService {

    // 1. Create a list of students
    ArrayList<Student> students = new ArrayList<>();

    // 2. Create CRUD endPoints
    // 2.1 Post(create)
    public void addStudent(Student student){
        students.add(student);
    }

    // 2.2 Get(read)
    public ArrayList<Student> getStudents(){
        return students;
    }

    // 2.3 Put(update)
    public boolean updateStudent(String ID, Student student){
        for (int i = 0; i < students.size(); i++) {
            if(students.get(i).getID().equalsIgnoreCase(ID)){
                students.set(i,student);
                return true;
            }
        }
        return false;
    }

    // 2.4 Delete
    public boolean deleteStudent(String ID){
        for (int i = 0; i < students.size(); i++) {
            if(students.get(i).getID().equalsIgnoreCase(ID)){
                students.remove(i);
                return true;
            }
        }
        return false;
    }

    // 3. Extra endPoints
    // 3.1 Method to get gpa average for all students
    public String getGpaAverage(){
        double average = 0;
        double total = 0;
        for (int i = 0; i < students.size(); i++) {
            total = total + students.get(i).getGPA();
        }
        average = total/students.size();
        return "Students GPA Average is " + average + ".";
    }

    // 3.2 Method to reward students based on certain conditions
    public boolean rewardStudents(){
        for (int i = 0; i < students.size(); i++) {
            if(students.get(i).getGPA() >= 4.5 && students.get(i).getAbsenceDays() == 0){
                students.get(i).setRewarded(true);
                return true;
            }
        }
        return false;
    }

    // 3.3 Method to return a list of students who exceeded the allowed absence days which is 6 days per semester
    public ArrayList<Student> exceededAllowedAbsenceDays(){
        ArrayList<Student> studentsExceededAllowedAbsenceDays = new ArrayList<>();
        for (Student student: students) {
            if(student.getAbsenceDays() > 6){
                studentsExceededAllowedAbsenceDays.add(student);
            }
        }
        if(studentsExceededAllowedAbsenceDays.isEmpty()){
            return null;
        }
        return studentsExceededAllowedAbsenceDays;
    }
}