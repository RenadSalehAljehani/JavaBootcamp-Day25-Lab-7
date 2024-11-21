package com.example.learningmanagementsystem.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Teacher {
    @NotEmpty(message = "ID can't be null.")
    @Size(min = 4, message = "ID must consists of more than 3 characters.")
    private String ID;
    @NotEmpty(message = "Name can't be null.")
    @Size(min = 4, max = 10, message = "Name length must be between 4-10 characters.")
    private String name;
    @NotNull(message = "Experience can't be null.")
    @PositiveOrZero(message = "Experience must be a positive number or zero.")
    private int experience;
    @NotNull(message = "Number of courses can't be null.")
    @PositiveOrZero(message = "Number of courses must be a positive number or zero.")
    private int numOfCourses;
    @NotNull(message = "Salary can't be null.")
    @PositiveOrZero(message = "Salary must be a positive number or zero.")
    private double salary;
}