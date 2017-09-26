package lab.datalayer;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Timestamp;


public class Guarantee {
    private final IntegerProperty id;
    private final StringProperty good;
    private final StringProperty agent;
    private final StringProperty warehouse;
    private final StringProperty good_num;
    private final IntegerProperty quantity;
    private final StringProperty break_inf;
    private final StringProperty status;
    private final StringProperty repairTime;

    public Guarantee(int _id, String _good, String _agent, String _warehouse, String _good_num,
                    int _quantity, String _break_inf, String _status, Timestamp _repairTime) {
        id = new SimpleIntegerProperty(_id);
        good = new SimpleStringProperty(_good);
        agent = new SimpleStringProperty(_agent);
        warehouse = new SimpleStringProperty(_warehouse);
        good_num = new SimpleStringProperty(_good_num);
        quantity = new SimpleIntegerProperty(_quantity);
        break_inf = new SimpleStringProperty(_break_inf);
        status = new SimpleStringProperty(_status);
        repairTime = new SimpleStringProperty(_repairTime == null ? null : _repairTime.toString());
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getGood() {
        return good.get();
    }

    public StringProperty goodProperty() {
        return good;
    }

    public String getAgent() {
        return agent.get();
    }

    public StringProperty agentProperty() {
        return agent;
    }

    public String getWarehouse() {
        return warehouse.get();
    }

    public StringProperty warehouseProperty() {
        return warehouse;
    }

    public String getType() {
        return good_num.get();
    }

    public StringProperty typeProperty() {
        return good_num;
    }

    public int getQuantity() {
        return quantity.get();
    }

    public IntegerProperty quantityProperty() {
        return quantity;
    }

    public String getDriver() {
        return break_inf.get();
    }

    public StringProperty driverProperty() {
        return break_inf;
    }

    public String getStatus() {
        return status.get();
    }

    public StringProperty statusProperty() {
        return status;
    }

    public String getGuaranteeTime() {
        return repairTime.get();
    }

    public StringProperty GuaranteeTimeProperty() {
        return repairTime;
    }
}