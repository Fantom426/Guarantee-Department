package lab;

import lab.datalayer.Database;
import lab.exception.DatabaseError;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class GoodsReportGenerator {

    private static final String delim = ";";

    private static class TableRow {
        private String nomenclature;
        private String name_ag;
        private String name;
        private String good_num;
        private Integer quantity;
        private String break_inf;
        private String status;

        private TableRow(String nomenclature, String name_ag, String name, String good_num, Integer quantity,
                         String break_inf, String status) {
            this.nomenclature = nomenclature;
            this.name_ag = name_ag;
            this.name = name;
            this.good_num = good_num;
            this.quantity = quantity;
            this.break_inf = break_inf;
            this.status = status;
        }
    }

    public static void generateReport(File output) {
        try {
            String query = "select * from VIEW_GUARANTEES_EVENT";
            PreparedStatement ps = Database.getInstance().getConnection()
                    .prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            List<TableRow> rows = new ArrayList<>();
            while (rs.next()) {
                rows.add(new TableRow(
                        rs.getString("NOMENCLATURE").trim(),
                        rs.getString("NAME_AG").trim(),
                        rs.getString("NAME").trim(),
                        rs.getString("GOOD_NUM").trim(),
                        rs.getInt("QUANTITY"),
                        rs.getString("BREAK_INF").trim(),
                        rs.getString("STATUS").trim()
                ));
            }
            writeToFile(output, rows);
        } catch (Exception e) {
            throw new DatabaseError(e);
        }
    }

    private static void writeToFile(File file, List<TableRow> data) {
        try {
            StringBuilder csv = new StringBuilder();
            csv.append("Товар").append(delim)
                    .append("Поставщик").append(delim)
                    .append("Склад").append(delim)
                    .append("Номер товара").append(delim)
                    .append("Количество").append(delim)
                    .append("Информация о поломке").append(delim)
                    .append("Состояние");
            for (TableRow row : data) {
                csv.append("\n\"").append(row.nomenclature).append("\"").append(delim)
                        .append("\"").append(row.name_ag).append("\"").append(delim)
                        .append("\"").append(row.name).append("\"").append(delim)
                        .append("\"").append(row.good_num).append("\"").append(delim)
                        .append("\"").append(row.quantity).append("\"").append(delim)
                        .append("\"").append(row.break_inf).append("\"").append(delim)
                        .append(row.status);
            }

            OutputStream os = new FileOutputStream(file);
            os.write(239); //make result encoding UTF-8 'with BOM'
            os.write(187);
            os.write(191);

            PrintWriter w = new PrintWriter(new OutputStreamWriter(os, "UTF-8"));

            w.print(csv.toString());
            w.flush();
            w.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
