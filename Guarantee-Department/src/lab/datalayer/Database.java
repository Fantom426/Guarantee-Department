package lab.datalayer;

import lab.exception.DatabaseError;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import static lab.util.CommonUtils.print;


public class Database {

    private static final Database database = new Database();

    private static Connection connection;
    private static Statement statement;

    private final Properties properties = new Properties();

    private Database() {
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("database/database.prop"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Database getInstance() {
        return database;
    }

    public Connection getConnection() {
        if (connection == null) {
            String driver = properties.getProperty("driver");
            String url = properties.getProperty("url");
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");

            try {
                Class.forName(driver);
                print("Firebird JDBC driver class found");
                connection = DriverManager.getConnection(url, user, password);
                print("connection successfully obtained");
            } catch (ClassNotFoundException e) {
                throw new DatabaseError("Firebird JDBC driver not found", e);
            } catch (Exception e) {
                throw new DatabaseError(e);
            }
        }
        return connection;
    }

    public Statement getStatement() {
        if (statement == null) {
            try {
                statement = getConnection().createStatement();
            } catch (SQLException e) {
                throw new DatabaseError(e);
            }
        }
        return statement;
    }

    public static void cleanUp() {
        if (connection != null)
            try {
                connection.close();
                print("connection closed");
            } catch (SQLException e) {
                throw new DatabaseError(e);
            }
    }
}