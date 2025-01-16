package services;

import javafx.scene.control.TextField;

public class TextFieldService {
    public static void populateStudentFields(String selectedStudent, TextField surnameField, TextField nameField, TextField middleNameField) {
        if (selectedStudent != null && !selectedStudent.isEmpty()) {
            String[] parts = selectedStudent.split(" ");
            if (parts.length >= 3) {
                surnameField.setText(parts[0]);
                nameField.setText(parts[1]);
                middleNameField.setText(parts[2]);
            }
        }
    }
}
