package lab.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lab.GuaranteeStatus;
import lab.datalayer.Database;
import lab.datalayer.Guarantee;
import lab.exception.DatabaseError;

import java.sql.*;

import static lab.util.CommonUtils.print;


public class GuaranteeService {
    public static ObservableList<Guarantee> findAll() {
        print("Guarantee.findAll");
        ObservableList<Guarantee> guarantees = FXCollections.observableArrayList();
        try {
            Statement statement = Database.getInstance().getStatement();
            ResultSet rs = statement.executeQuery("select * from VIEW_GUARANTEE");
            while (rs.next()) {
                String stat = rs.getString("status");
                GuaranteeStatus guaranteeStatus = GuaranteeStatus.fromValue(stat);
                stat = guaranteeStatus.toString();

                guarantees.add(new Guarantee(rs.getInt("id"),
                        rs.getString("NOMENCLATURE").trim(),
                        rs.getString("NAME_AG").trim(),
                        rs.getString("name").trim(),
                        rs.getString("good_num"),
                        rs.getInt("QUANTITY"),
                        rs.getString("break_inf").trim(),
                        stat,
                        rs.getTimestamp("ADM_DATE")));
            }
        } catch (SQLException e) {
            System.out.println("SQLException " + e.getMessage());
        }
        return guarantees;
    }

    //создание новой доставки
    public static void createNewGuarantee(String _agent, String _warehouse, String _good,
                                         int _quantity, int _price, String _good_num, String _break_inf) {
        try {
            PreparedStatement ps = Database.getInstance().getConnection()
                    .prepareStatement("{call create_guarantee(?, ?, ?, ?, ?, ?, ?)}");
            ps.setString(1, _agent);
            ps.setString(2, _warehouse);
            ps.setString(3, _good);
            ps.setInt(4, _quantity);
            ps.setInt(5, _price);
            ps.setString(6, _good_num);
            ps.setString(7, _break_inf);
            ps.execute();
        } catch (SQLException e) {
            throw new DatabaseError(e);
        }
    }

    public static void good_repaired(Guarantee guarantee) {
        try {
            String query = "{call repaired(?)}";
            PreparedStatement ps = Database.getInstance().getConnection().prepareStatement(query);
            ps.setInt(1, guarantee.getId());
            ps.execute();
        } catch (SQLException e) {
            throw new DatabaseError(e);
        }
    }

    public static void not_Guarantee(Guarantee guarantee) {
        try {
            String query = "{call not_guarantee(?)}";
            PreparedStatement ps = Database.getInstance().getConnection().prepareStatement(query);
            ps.setInt(1, guarantee.getId());
            ps.execute();
        } catch (SQLException e) {
            throw new DatabaseError(e);
        }
    }

    public static void cancelGuarantee(Guarantee guarantee) {
        try {
            String query = "{call not_repaired(?)}";
            PreparedStatement ps = Database.getInstance().getConnection().prepareStatement(query);
            ps.setInt(1, guarantee.getId());
            ps.execute();
        } catch (SQLException e) {
            throw new DatabaseError(e);
        }
    }


}
