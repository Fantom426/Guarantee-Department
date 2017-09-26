package lab.datalayer;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Agent {
    private final StringProperty id;
    private final StringProperty name;
    private final StringProperty town;
    private final StringProperty phone;


    public Agent(String _id, String _name, String _town, String _phone) {
        id = new SimpleStringProperty(_id);
        name = new SimpleStringProperty(_name);
        town = new SimpleStringProperty(_town);
        phone = new SimpleStringProperty(_phone);

    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getTown() {
        return town.get();
    }

    public StringProperty townProperty() {
        return town;
    }

    public String getPhone() {
        return phone.get();
    }

    public StringProperty phoneProperty() {
        return phone;
    }


    @Override
    public String toString() {
        return getName();
    }
}

