package com.example.diagnosisinformationapp.diagnosis;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.BooleanProperty;

public class Diagnosis {
    private final StringProperty symptoms;
    private final StringProperty diagnosis;
    private final StringProperty medicines;
    private final BooleanProperty wardRequired;

    public Diagnosis(String symptoms, String diagnosis, String medicines, boolean wardRequired) {
        this.symptoms = new SimpleStringProperty(symptoms);
        this.diagnosis = new SimpleStringProperty(diagnosis);
        this.medicines = new SimpleStringProperty(medicines);
        this.wardRequired = new SimpleBooleanProperty(wardRequired);
    }

    public StringProperty symptomsProperty() {
        return symptoms;
    }

    public StringProperty diagnosisProperty() {
        return diagnosis;
    }

    public StringProperty medicinesProperty() {
        return medicines;
    }

    public BooleanProperty wardRequiredProperty() {
        return wardRequired;
    }
}
