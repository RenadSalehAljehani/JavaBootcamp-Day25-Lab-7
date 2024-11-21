package com.example.learningmanagementsystem.Service;

import com.example.learningmanagementsystem.Model.Teacher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class TeacherService {

    // 1. Create a list of teachers
    ArrayList<Teacher> teachers = new ArrayList<>();

    // 2. Create CRUD endPoints
    // 2.1 Post(create)
    public void addTeacher(Teacher teacher){
        teachers.add(teacher);
    }

    // 2.2 Get(read)
    public ArrayList<Teacher> getTeachers(){
        return teachers;
    }

    // 2.3 Put(update)
    public boolean updateTeacher(String ID, Teacher teacher){
        for (int i = 0; i < teachers.size(); i++) {
            if(teachers.get(i).getID().equalsIgnoreCase(ID)){
                teachers.set(i,teacher);
                return true;
            }
        }
        return false;
    }

    // 2.4 Delete
    public boolean deleteTeacher(String ID){
        for (int i = 0; i < teachers.size(); i++) {
            if(teachers.get(i).getID().equalsIgnoreCase(ID)){
                teachers.remove(i);
                return true;
            }
        }
        return false;
    }

    // 3. Extra endPoints
    // 3.1 Method to return a list of teachers with specific years of experience
    public ArrayList<Teacher> searchByExperience(int experience){
        ArrayList<Teacher> teachersWithSpecificExperienceList = new ArrayList<>();
        for (Teacher teacher: teachers){
            if(teacher.getExperience() == experience){
                teachersWithSpecificExperienceList.add(teacher);
            }
        }
        if(teachersWithSpecificExperienceList.isEmpty()){
            return null;
        }
        return teachersWithSpecificExperienceList;
    }

    // 3.2 Method to return list of teachers who exceeded allowed number of courses which is 8 courses per semester
    public ArrayList<Teacher> teachersExceededAllowedNumOfCourses(){
        ArrayList<Teacher> teachersExceededAllowedNumOfCoursesList = new ArrayList<>();
        for (Teacher teacher: teachers){
            if(teacher.getNumOfCourses() > 8){
                teachersExceededAllowedNumOfCoursesList.add(teacher);
            }
        }
        if(teachersExceededAllowedNumOfCoursesList.isEmpty()){
            return null;
        }
        return teachersExceededAllowedNumOfCoursesList;

    }

    // 3.3 Method to reward teachers who have more than 5 years of experience
    public boolean rewardTeachers(){
        for (Teacher teacher: teachers){
            if(teacher.getExperience() > 5){
                teacher.setSalary(teacher.getSalary() + 500);
                return true;
            }
        }
        return false;
    }
}