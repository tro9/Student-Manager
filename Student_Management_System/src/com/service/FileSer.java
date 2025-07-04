package com.service;

import com.model.Student;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileSer {
    private static final String FILE_PATH = "students.dat";

    // Save students to file
    public static void saveStudents(List<Student> students) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(students);
            System.out.println("Data saved successfully!");
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }

    // Load students from file
    public static List<Student> loadStudents() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            return (List<Student>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading data: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}