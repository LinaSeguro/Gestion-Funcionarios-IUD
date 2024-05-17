
package com.iudigital.employee.center.dao;

import com.iudigital.employee.center.domain.TipoDocumento;
import com.iudigital.employee.center.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoDocumentoDao {

    private static final String GET_TIPOS_DOCUMENTO = "SELECT tipo_documento_id, codigo, nombre FROM recursos_humanos_iud.tipo_documento";
    private static final String GET_TIPO_DOCUMENTO_ID = "SELECT tipo_documento_id FROM recursos_humanos_iud.tipo_documento WHERE tipo_documento_id= ?";

    public List<TipoDocumento> obtenerTiposDocumento() throws SQLException {
        List<TipoDocumento> tiposDocumento = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(GET_TIPOS_DOCUMENTO);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idTipoDocumento = resultSet.getInt("tipo_documento_id");
                String codigoTipoDocumento = resultSet.getString("codigo");
                String nombreTipoDocumento = resultSet.getString("nombre");
                TipoDocumento tipoDocumento = new TipoDocumento();
                tipoDocumento.setTipoDocumentoId(idTipoDocumento);
                tipoDocumento.setCodigo(codigoTipoDocumento);
                tipoDocumento.setNombre(nombreTipoDocumento);
                tiposDocumento.add(tipoDocumento);
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
        return tiposDocumento;
    }

    public static int obtenerTipoDocumentoId(String tipoDocumento) throws SQLException {
        int tipoDocumentoId = -1;

        try (
                Connection connection = ConnectionUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(GET_TIPO_DOCUMENTO_ID);) {
            preparedStatement.setString(1, tipoDocumento);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    tipoDocumentoId = resultSet.getInt("tipo_documento_id");
                }
            }
        }
        return tipoDocumentoId;
    }

}
