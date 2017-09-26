package lab.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import lab.View;
import lab.datalayer.Agent;
import lab.service.AgentService;
import lab.util.FXUtils;


public class AgentsController extends FXController {
    public Button backBtn;
    public TableView<Agent> agentsTableView;
    public TableColumn<Agent, String> nameTc;
    public TableColumn<Agent, String> townTc;
    public TableColumn<Agent, String> phoneTc;

    @Override
    public void initialize() {
        super.initialize();

        nameTc.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        townTc.setCellValueFactory(cellData -> cellData.getValue().townProperty());
        phoneTc.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());


        agentsTableView.setItems(AgentService.findAll());
    }

    public void goBack(ActionEvent actionEvent) {
        FXUtils.setCurrentView(View.Home);
    }
}