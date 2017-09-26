package lab.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import lab.View;
import lab.datalayer.Agent;
import lab.datalayer.Good;
import lab.datalayer.Warehouse;
import lab.exception.DatabaseError;
import lab.service.AgentService;
import lab.service.GuaranteeService;
import lab.service.GoodService;
import lab.service.WarehouseService;
import lab.util.FXUtils;

import java.sql.SQLException;

import static lab.util.CommonUtils.print;


public class NewGuaranteeController extends FXController {
    public ChoiceBox<Warehouse> warehouseCb;
    public ChoiceBox<Good> goodCb;
    public ChoiceBox<Agent> agentCb;
    public TextField good_numTf;
    public TextField quantityTf;
    public TextField break_infTf;
    public TextField priceTf;
    public TextField repairTimeTf;
    public Button createBtn;
    public Button backBtn;


    @Override
    public void initialize() {
        super.initialize();

        //make text fields numeric only
        quantityTf.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                quantityTf.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        priceTf.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                priceTf.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        ObservableList<Warehouse> warehouses = WarehouseService.findAll();
        warehouseCb.setItems(warehouses);
        warehouseCb.getSelectionModel().select(0);

        ObservableList<Good> goods = GoodService.findAll();
        goodCb.setItems(goods);
        goodCb.getSelectionModel().select(0);

        ObservableList<Agent> agents = AgentService.findAll();
        agentCb.setItems(agents);
        agentCb.getSelectionModel().select(0);
    }

    public void createGuarantee(ActionEvent actionEvent) {
        try {
            checkValues();
            checkQuantity();
        } catch (IllegalArgumentException e) {
            return;
        }
        try {
            GuaranteeService.createNewGuarantee(agentCb.getValue().getName(),
                    warehouseCb.getValue().getName(),
                    goodCb.getValue().getNomenclature(),
                    toInt(quantityTf.getText()),
                    toInt(priceTf.getText()),
                    break_infTf.getText(),
                    repairTimeTf.getText());
        } catch (DatabaseError e) {
            SQLException cause = (SQLException) e.getCause();
            throw e;
        }
        FXUtils.setCurrentView(View.guarantees);
    }

    private void checkQuantity() {
        int deliverQuantity = toInt(quantityTf.getText());
        if (deliverQuantity == 0) {
            new Alert(Alert.AlertType.WARNING, "quantity shouldn't be 0").show();
            throw new IllegalArgumentException("quantity cannot be 0");
        }
    }

    private void checkValues() {
        checkIsFilled("warehouse", warehouseCb);
        checkIsFilled("good", goodCb);
        checkIsFilled("agent", agentCb);
        checkIsFilled("good_num", good_numTf);
        checkIsFilled("quantity", quantityTf);
        checkIsFilled("break_inf", break_infTf);
        checkIsFilled("price", priceTf);
        checkIsFilled("repairTime", repairTimeTf);
    }

    private void checkIsFilled(String fieldName, ChoiceBox choiceBox) {
        if (choiceBox.getValue() == null) {
            choiceBox.requestFocus();
            print(fieldName + " is not filled");
            throw new IllegalArgumentException(fieldName + " is not filled");
        }
    }

    private void checkIsFilled(String fieldName, TextField textField) {
        if (textField.getText() == null || textField.getText().equals("")) {
            textField.requestFocus();
            print(fieldName + " is not filled");
            throw new IllegalArgumentException(fieldName + " is not filled");
        }
    }

    public void goBack(ActionEvent actionEvent) {
        FXUtils.setCurrentView(View.Home);
    }

    private int toInt(String from) {
        return Integer.valueOf(from);
    }
}