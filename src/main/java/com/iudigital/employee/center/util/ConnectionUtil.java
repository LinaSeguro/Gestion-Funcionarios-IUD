
package com.iudigital.employee.center.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

    private static final String URL = "jdbc:mysql://localhost:3306/recursos_humanos_iud";

    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
        System.out.println("Conexión exitosa a la base de datos.");
        return connection;
    }

}
