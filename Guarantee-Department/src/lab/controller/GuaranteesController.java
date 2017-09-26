package lab.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import lab.GuaranteeStatus;
import lab.View;
import lab.datalayer.Guarantee;
import lab.service.GuaranteeService;
import lab.util.FXUtils;

import java.time.LocalDate;
import java.util.Arrays;


public class GuaranteesController extends FXController {
    public Button backBtn;
    public TableColumn<Guarantee, String> goodColumn;
    public TableColumn<Guarantee, String> agentColumn;
    public TableColumn<Guarantee, String> warehouseColumn;
    public TableColumn<Guarantee, String> good_numColumn;
    public TableColumn<Guarantee, Integer> quantityColumn;
    public TableColumn<Guarantee, String> break_infColumn;
    public TableColumn<Guarantee, String> statusColumn;
    public TableColumn<Guarantee, String> guaranteeTimeColumn;
    public TableView<Guarantee> guarantiesTableView;
    public Button guaranteeBtn;
    public Button cancelBtn;
    public Button notBtn;


    private Guarantee selectedGuarantee;

    @Override
    public void initialize() {
        super.initialize();

        goodColumn.setCellValueFactory(cellData -> cellData.getValue().goodProperty());
        agentColumn.setCellValueFactory(cellData -> cellData.getValue().agentProperty());
        warehouseColumn.setCellValueFactory(cellData -> cellData.getValue().warehouseProperty());
        good_numColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        quantityColumn.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());
        break_infColumn.setCellValueFactory(cellData -> cellData.getValue().driverProperty());
        statusColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
        guaranteeTimeColumn.setCellValueFactory(cellData -> cellData.getValue().GuaranteeTimeProperty());

        guarantiesTableView.setItems(GuaranteeService.findAll());
        guarantiesTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                selectedGuarantee = newValue;
                String guaranteeStatus = selectedGuarantee.getStatus();
                if (GuaranteeStatus.Process.toString().equals(guaranteeStatus)) { //very ugly, I know
                    enableButtons();
                } else {
                    disableButtons();
                }
            }
        });


    }

    private void enableButtons() {
        guaranteeBtn.setDisable(false);
        cancelBtn.setDisable(false);
        notBtn.setDisable(false);
    }

    private void disableButtons() {
        guaranteeBtn.setDisable(true);
        cancelBtn.setDisable(true);
        notBtn.setDisable(true);
    }

    public void goBack(ActionEvent actionEvent) {
        FXUtils.setCurrentView(View.Home);
    }

    public void repaired(ActionEvent actionEvent) {
        GuaranteeService.good_repaired(selectedGuarantee);
        selectedGuarantee.statusProperty().setValue(GuaranteeStatus.Repaired.toString());
        disableButtons();
    }

    public void notGuarantee(ActionEvent actionEvent) {
        GuaranteeService.not_Guarantee(selectedGuarantee);
        selectedGuarantee.statusProperty().setValue(GuaranteeStatus.Not_guarantee.toString());
        disableButtons();
    }

    public void notRepairable(ActionEvent actionEvent) {
        GuaranteeService.cancelGuarantee(selectedGuarantee);
        selectedGuarantee.statusProperty().setValue(GuaranteeStatus.Repair_impossible.toString());
        disableButtons();
    }
}
