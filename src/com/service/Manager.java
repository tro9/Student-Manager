package com.service;

import com.model.Student;
import java.util.*;

public class Manager {
    private List<Student> students = new ArrayList<>();
    // Add a student
    public void addStudent(Student student) {
        students.add(student);
    }

    // Edit a student
    public boolean editStudent(String id, String newName, int newAge, double newGrade) {
        for (Student s : students) {
            if (s.getId().equals(id)) {
                s.setName(newName);
                s.setAge(newAge);
                s.setGrade(newGrade);
                return true;
            }
        }
        return false;
    }

    // Delete a student
    public boolean deleteStudent(String id) {
        return students.removeIf(s -> s.getId().equals(id));
    }

    // Search by ID
    public Student searchById(String id) {
        return students.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Search by name
    public List<Student> searchByName(String name) {
        return students.stream()
                .filter(s -> s.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }

    // Sort by name
    public List<Student> sortByName() {
        return students.stream()
                .sorted(Comparator.comparing(Student::getName))
                .toList();
    }

    // Sort by grade (descending)
    public List<Student> sortByGrade() {
        return students.stream()
                .sorted(Comparator.comparingDouble(Student::getGrade).reversed())
                .toList();
    }

    // Get all students
    public List<Student> getAllStudents() {
        return students;
    }
}