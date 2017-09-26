package lab.datalayer;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class GoodsWh {
    private final StringProperty id;
    private final StringProperty warehouseId;
    private final StringProperty goodsId;
    private final IntegerProperty quantity;

    public GoodsWh(String id, String warehouseId, String goodsId, int quantity) {
        this.id = new SimpleStringProperty(id);
        this.warehouseId = new SimpleStringProperty(warehouseId);
        this.goodsId = new SimpleStringProperty(goodsId);
        this.quantity = new SimpleIntegerProperty(quantity);
    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public String getWarehouseId() {
        return warehouseId.get();
    }

    public StringProperty warehouseIdProperty() {
        return warehouseId;
    }

    public String getGoodsId() {
        return goodsId.get();
    }

    public StringProperty goodsIdProperty() {
        return goodsId;
    }

    public int getQuantity() {
        return quantity.get();
    }

    public IntegerProperty quantityProperty() {
        return quantity;
    }
}
