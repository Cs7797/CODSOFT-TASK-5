import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application {
    private StudentManagementSystem sms = new StudentManagementSystem();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Student Management System");

        
        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();
        Label rollNumberLabel = new Label("Roll Number:");
        TextField rollNumberField = new TextField();
        Label gradeLabel = new Label("Grade:");
        TextField gradeField = new TextField();
        Button addButton = new Button("Add Student");
        Button displayButton = new Button("Display All Students");
        TextArea outputArea = new TextArea();
        outputArea.setEditable(false);
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);
        grid.add(nameLabel, 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(rollNumberLabel, 0, 1);
        grid.add(rollNumberField, 1, 1);
        grid.add(gradeLabel, 0, 2);
        grid.add(gradeField, 1, 2);
        grid.add(addButton, 1, 3);
        grid.add(displayButton, 1, 4);
        grid.add(outputArea, 0, 5, 2, 1);
        addButton.setOnAction(e -> {
            String name = nameField.getText();
            String grade = gradeField.getText();
            int rollNumber;
            try {
                rollNumber = Integer.parseInt(rollNumberField.getText());
            } catch (NumberFormatException ex) {
                showAlert("Error", "Roll Number must be an integer.");
                return;
            }

            if (name.isEmpty() || grade.isEmpty()) {
                showAlert("Error", "Name and Grade cannot be empty.");
                return;
            }

            Student student = new Student(name, rollNumber, grade);
            sms.addStudent(student);
            nameField.clear();
            rollNumberField.clear();
            gradeField.clear();
        });
        displayButton.setOnAction(e -> {
            List<Student> students = sms.getAllStudents();
            StringBuilder sb = new StringBuilder();
            for (Student student : students) {
                sb.append(student).append("\n");
            }
            outputArea.setText(sb.toString());
        });
        Scene scene = new Scene(grid, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}