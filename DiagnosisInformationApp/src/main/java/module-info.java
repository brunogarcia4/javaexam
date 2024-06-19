module com.example.diagnosisinformationapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.diagnosisinformationapp.diagnosis to javafx.fxml;
    exports com.example.diagnosisinformationapp.diagnosis;
}
