package lab.datalayer;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Good {
    private final StringProperty id;
    private final StringProperty nomenclature;
    private final StringProperty measure;

    public Good(String _id, String _nomenclature, String _measure) {
        id = new SimpleStringProperty(_id);
        nomenclature = new SimpleStringProperty(_nomenclature);
        measure = new SimpleStringProperty(_measure);
    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public String getNomenclature() {
        return nomenclature.get();
    }

    public StringProperty nomenclatureProperty() {
        return nomenclature;
    }

    public String getMeasure() {
        return measure.get();
    }

    public StringProperty measureProperty() {
        return measure;
    }

    @Override
    public String toString() {
        return getNomenclature();
    }
}