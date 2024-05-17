
package com.iudigital.employee.center.dao;

import com.iudigital.employee.center.domain.EstadoCivil;
import com.iudigital.employee.center.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstadoCivilDao {

    private static final String GET_ESTADOS_CIVILES = "SELECT estado_civil_id, nombre FROM recursos_humanos_iud.estado_civil";
    private static final String GET_ESTADO_CIVIL_ID = "SELECT estado_civil_id FROM recursos_humanos_iud.estado_civil WHERE estado_civil_id = ?";

    public List<EstadoCivil> obtenerEstadosCiviles() throws SQLException {
        List<EstadoCivil> estadosCiviles = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(GET_ESTADOS_CIVILES);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idEstadoCivil = resultSet.getInt("estado_civil_id");
                String nombreEstadoCivil = resultSet.getString("nombre");
                EstadoCivil estadoCivil = new EstadoCivil();
                estadoCivil.setEstadoCivilId(idEstadoCivil);
                estadoCivil.setNombre(nombreEstadoCivil);
                estadosCiviles.add(estadoCivil);
            }
        } finally {
            // Cerrar recursos
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return estadosCiviles;
    }

    public static int obtenerEstadoCivilId(String estadoCivil) throws SQLException {
        int estadoCivilId = -1;

        try (
                Connection connection = ConnectionUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(GET_ESTADO_CIVIL_ID);) {
            preparedStatement.setString(1, estadoCivil);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    estadoCivilId = resultSet.getInt("estado_civil_id");
                }
            }
        }
        return estadoCivilId;
    }
}
