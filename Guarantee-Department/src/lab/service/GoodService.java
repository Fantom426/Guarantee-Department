package lab.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lab.datalayer.Database;
import lab.datalayer.Good;
import lab.exception.DatabaseError;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static lab.util.CommonUtils.print;


public class GoodService {
    public static ObservableList<Good> findAll() {
        print("Good.findAll");
        ObservableList<Good> goods = FXCollections.observableArrayList();
        try {
            Statement statement = Database.getInstance().getStatement();
            ResultSet rs = statement.executeQuery("select * from VIEW_GOODS");
            while (rs.next()) {
                goods.add(new Good(rs.getString("id_goods").trim(),
                        rs.getString("nomenclature").trim(),
                        rs.getString("measure").trim()));
            }
        } catch (SQLException e) {
            throw new DatabaseError(e);
        }
        return goods;
    }
}
