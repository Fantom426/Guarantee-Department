package lab.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import lab.datalayer.Agent;
import lab.datalayer.Guarantee;
import lab.datalayer.Good;
import lab.service.AgentService;
import lab.service.GuaranteeService;
import lab.service.GoodService;

import static lab.util.CommonUtils.print;

public class MainController extends FXController {
    //Guarantee
    @FXML
    private TableView<Guarantee> GuaranteeTableView;
    @FXML
    private TableColumn<Guarantee, String> goodTableColumn;
    @FXML
    private TableColumn<Guarantee, String> warehouseTableColumn;
    @FXML
    private TableColumn<Guarantee, String> good_numTableColumn;
    @FXML
    private TableColumn<Guarantee, Integer> quantityTableColumn;
    @FXML
    private TableColumn<Guarantee, String> break_infTableColumn;
    @FXML
    private TableColumn<Guarantee, String> statusTableColumn;


    //AGENT
    @FXML
    private TableView<Agent> agentTableView;
    @FXML
    private TableColumn<Agent, String> nameTableColumn;
    @FXML
    private TableColumn<Agent, String> townTableColumn;
    @FXML
    private TableColumn<Agent, String> phoneTableColumn;
    @FXML
    private TableColumn<Agent, Integer> payTableColumn;

    //Good
    @FXML
    private TableView<Good> goodTableView;
    @FXML
    private TableColumn<Good, String> nomenclatureTableColumn;
    @FXML
    private TableColumn<Good, String> measureTableColumn;

    //UI CONTROLS
    @FXML
    private Button refreshButton;

    public MainController() {
    }

    @Override
    public void initialize() {
        print("initialize");

        //Guarantee
        goodTableColumn.setCellValueFactory(cellData -> cellData.getValue().goodProperty());
        warehouseTableColumn.setCellValueFactory(cellData -> cellData.getValue().warehouseProperty());
        good_numTableColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        quantityTableColumn.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());
        warehouseTableColumn.setCellValueFactory(cellData -> cellData.getValue().warehouseProperty());
        break_infTableColumn.setCellValueFactory(cellData -> cellData.getValue().driverProperty());
        statusTableColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());

        //Agent
        nameTableColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        townTableColumn.setCellValueFactory(cellData -> cellData.getValue().townProperty());
        phoneTableColumn.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());


        //Good
        nomenclatureTableColumn.setCellValueFactory(cellData -> cellData.getValue().nomenclatureProperty());
        measureTableColumn.setCellValueFactory(cellData -> cellData.getValue().measureProperty());

        postGuaranteeTableView();
        postAgentTableView();
        postGoodTableView();
    }

    public void postGuaranteeTableView() {
        print("postGuaranteeTableView");
        GuaranteeTableView.setItems(GuaranteeService.findAll());
    }

    public void postAgentTableView() {
        print("postAgentTableView");
        agentTableView.setItems(AgentService.findAll());
    }

    public void postGoodTableView() {
        print("postGoodTableView");
        goodTableView.setItems(GoodService.findAll());
    }

    @FXML
    public void handleRefreshAll() {
        refreshButton.disarm();
        postGoodTableView();
        postAgentTableView();
        postGuaranteeTableView();
    }
}