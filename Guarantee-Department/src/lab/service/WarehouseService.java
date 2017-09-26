package lab.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lab.datalayer.Database;
import lab.datalayer.Good;
import lab.datalayer.Warehouse;
import lab.exception.DatabaseError;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class WarehouseService {
    public static ObservableList<Warehouse> findAll() {
        try {
            ResultSet rs = Database.getInstance().getStatement()
                    .executeQuery("select * from WAREHOUSE");
            ObservableList<Warehouse> warehouseList = FXCollections.observableArrayList();
            while (rs.next()) {
                warehouseList.add(new Warehouse(
                        rs.getString("ID_WH").trim(),
                        rs.getString("NAME").trim(),
                        rs.getString("TOWN").trim()
                ));
            }
            return warehouseList;
        } catch (SQLException e) {
            throw new DatabaseError(e);
        }
    }

    @Deprecated
    public static int findGoodsQuantity(Warehouse warehouse, Good good) {
        try {
            String query = "select QUANTITY from GOODS_WH where ID_WH=? and ID_GOODS=?";
            PreparedStatement ps = Database.getInstance().getConnection()
                    .prepareStatement(query);
            ps.setString(1, warehouse.getId());
            ps.setString(2, good.getId());
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                return 0;
            }
            return rs.getInt("QUANTITY");
        } catch (SQLException e) {
            throw new DatabaseError(e);
        }
    }
}
