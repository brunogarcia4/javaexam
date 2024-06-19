package com.example.diagnosisinformationapp.diagnosis;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DiagnosisInformationAppController {

    @FXML
    private TextField patientIDField;
    @FXML
    private TextField symptomsField;
    @FXML
    private TextField diagnosisField;
    @FXML
    private TextField medicinesField;
    @FXML
    private CheckBox wardRequiredCheckBox;
    @FXML
    private TableView<Diagnosis> diagnosisTable;
    @FXML
    private TableColumn<Diagnosis, String> symptomsColumn;
    @FXML
    private TableColumn<Diagnosis, String> diagnosisColumn;
    @FXML
    private TableColumn<Diagnosis, String> medicinesColumn;
    @FXML
    private TableColumn<Diagnosis, Boolean> wardRequiredColumn;

    private Connection conn;

    public DiagnosisInformationAppController() {
        conn = DatabaseConnector.connect();
    }

    @FXML
    private void saveDiagnosis(ActionEvent event) {
        String patientID = patientIDField.getText();
        String symptoms = symptomsField.getText();
        String diagnosis = diagnosisField.getText();
        String medicines = medicinesField.getText();
        boolean wardRequired = wardRequiredCheckBox.isSelected();

        String query = "INSERT INTO Diagnosis (patientID, symptoms, diagnosis, medicines, wardRequired) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, patientID);
            pstmt.setString(2, symptoms);
            pstmt.setString(3, diagnosis);
            pstmt.setString(4, medicines);
            pstmt.setBoolean(5, wardRequired);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void searchDiagnosis(ActionEvent event) {
        String patientID = patientIDField.getText();
        String query = "SELECT * FROM Diagnosis WHERE patientID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, patientID);
            ResultSet rs = pstmt.executeQuery();
            diagnosisTable.getItems().clear();
            while (rs.next()) {
                Diagnosis diagnosis = new Diagnosis(
                        rs.getString("symptoms"),
                        rs.getString("diagnosis"),
                        rs.getString("medicines"),
                        rs.getBoolean("wardRequired")
                );
                diagnosisTable.getItems().add(diagnosis);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void closeApplication(ActionEvent event) {
        Stage stage = (Stage) diagnosisTable.getScene().getWindow();
        stage.close();
    }
}
