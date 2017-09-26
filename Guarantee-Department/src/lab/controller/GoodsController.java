package lab.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import lab.View;
import lab.datalayer.Good;
import lab.service.GoodService;
import lab.util.FXUtils;


public class GoodsController extends FXController {
    public Button backBtn;
    public TableView<Good> goodsTableView;
    public TableColumn<Good, String> measureTc;
    public TableColumn<Good, String> nomenclatureTc;

    @Override
    public void initialize() {
        super.initialize();

        nomenclatureTc.setCellValueFactory(cellData -> cellData.getValue().nomenclatureProperty());
        measureTc.setCellValueFactory(cellData -> cellData.getValue().measureProperty());

        goodsTableView.setItems(GoodService.findAll());
    }

    public void goBack(ActionEvent actionEvent) {
        FXUtils.setCurrentView(View.Home);
    }
}