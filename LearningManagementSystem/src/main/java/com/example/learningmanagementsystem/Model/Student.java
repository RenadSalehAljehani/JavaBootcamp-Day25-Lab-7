package com.example.learningmanagementsystem.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {
    @NotEmpty(message = "ID can't be null.")
    @Size(min = 4, message = "ID must consists of more than 3 characters.")
    private String ID;
    @NotEmpty(message = "Name can't be null.")
    @Size(min = 4, max = 10, message = "Name length must be between 4-10 characters.")
    private String name;
    @NotNull(message = "GPA can't be null.")
    @Max(value = 5, message = "GPA is out of 5.")
    @PositiveOrZero
    private double GPA;
    @NotNull(message = "Age can't be null.")
    @Min(value = 18, message = "Age can't be less than 18.")
    private int age;
    @AssertFalse(message = "IsRewarded initial value must be false.")
    private boolean isRewarded;
    @NotNull(message = "Absence days can't be null.")
    @PositiveOrZero(message = "Absence days must be a positive number or zero.")
    private int absenceDays;
}