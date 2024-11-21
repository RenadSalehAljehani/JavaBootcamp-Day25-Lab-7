package com.example.learningmanagementsystem.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Course {
    @NotEmpty(message = "ID can't be null.")
    @Size(min = 4, message = "ID must consists of more than 3 characters.")
    private String ID;
    @NotEmpty(message = "Title can't be null.")
    @Size(min = 4, max = 10, message = "Title length must be between 4-10 characters.")
    private String title;
    @NotEmpty(message = "Description can't be null.")
    @Size(min = 10, max = 100, message = "Description length must be between 10-100 characters.")
    private String description;
    @PositiveOrZero(message = "Rating must be a positive number or zero.")
    @Max(value = 5 , message = "Rating must ne between 0-5")
    private double rating;
    @NotNull(message = "Number of student registered can't be null.")
    @PositiveOrZero(message = "Number of student registered must be a positive number or zero.")
    private int numOfStudentRegistered;
    @AssertFalse(message = "Is full initial value must be false.")
    private boolean isFull;
}