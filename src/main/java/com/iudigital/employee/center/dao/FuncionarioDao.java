package com.iudigital.employee.center.dao;

import com.iudigital.employee.center.domain.Funcionario;
import com.iudigital.employee.center.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDao {

    private static final String GET_FUNCIONARIOS
            = "SELECT recursos_humanos_iud.funcionarios.funcionario_id, "
            + "recursos_humanos_iud.tipo_documento.codigo AS tipo_documento, "
            + "recursos_humanos_iud.funcionarios.numero_identificacion, "
            + "recursos_humanos_iud.funcionarios.nombres, "
            + "recursos_humanos_iud.funcionarios.apellidos, "
            + "recursos_humanos_iud.funcionarios.sexo, "
            + "recursos_humanos_iud.funcionarios.direccion, "
            + "recursos_humanos_iud.funcionarios.telefono, "
            + "recursos_humanos_iud.funcionarios.fecha_nacimiento, "
            + "recursos_humanos_iud.estado_civil.nombre AS estado_civil "
            + "FROM recursos_humanos_iud.funcionarios "
            + "JOIN recursos_humanos_iud.estado_civil "
            + "    ON recursos_humanos_iud.funcionarios.estado_civil_id = recursos_humanos_iud.estado_civil.estado_civil_id "
            + "JOIN recursos_humanos_iud.tipo_documento "
            + "    ON recursos_humanos_iud.funcionarios.tipo_documento_id = recursos_humanos_iud.tipo_documento.tipo_documento_id";

    private static final String CREATE_FUNCIONARIO = "INSERT INTO Funcionarios (tipo_documento_id, "
        + "numero_identificacion, nombres, apellidos, estado_civil_id, sexo, direccion, telefono, fecha_nacimiento) "
        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String GET_FUNCIONARIO_BY_ID
            = "SELECT funcionarios.funcionario_id, "
            + "       tipo_documento.codigo AS tipo_documento, "
            + "       funcionarios.numero_identificacion, "
            + "       funcionarios.nombres, "
            + "       funcionarios.apellidos, "
            + "       estado_civil.nombre AS estado_civil, " 
            + "       funcionarios.sexo, "
            + "       funcionarios.direccion, "
            + "       funcionarios.telefono, "
            + "       funcionarios.fecha_nacimiento "
            + "FROM recursos_humanos_iud.funcionarios "
            + "JOIN recursos_humanos_iud.estado_civil "
            + "    ON recursos_humanos_iud.funcionarios.estado_civil_id = recursos_humanos_iud.estado_civil.estado_civil_id "
            + "JOIN recursos_humanos_iud.tipo_documento "
            + "    ON recursos_humanos_iud.funcionarios.tipo_documento_id = recursos_humanos_iud.tipo_documento.tipo_documento_id "
            + "WHERE funcionarios.funcionario_id = ?";

    private static final String UPDATE_FUNCIONARIO
            = "UPDATE Funcionarios "
            + "SET tipo_documento_id = ?, "
            + "    numero_identificacion = ?, "
            + "    nombres = ?, "
            + "    apellidos = ?, "
            + "    estado_civil_id = ?, "
            + "    sexo = ?, "
            + "    direccion = ?, "
            + "    telefono = ?, "
            + "    fecha_nacimiento = ? "
            + "WHERE funcionario_id = ?";

    private static final String DELETE_FUNCIONARIO = "DELETE FROM Funcionarios WHERE funcionario_id = ?";

    public List<Funcionario> obtenerFuncionarios() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Funcionario> funcionarios = new ArrayList<>();

        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(GET_FUNCIONARIOS);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setFuncionarioId(resultSet.getInt("funcionario_id"));
                funcionario.setTipoDocumento(resultSet.getString("tipo_documento"));
                funcionario.setNumeroIdentificacion(resultSet.getString("numero_identificacion"));
                funcionario.setNombres(resultSet.getString("nombres"));
                funcionario.setApellidos(resultSet.getString("apellidos"));
                funcionario.setEstadoCivil(resultSet.getString("estado_civil"));
                funcionario.setSexo(resultSet.getString("sexo"));
                funcionario.setDireccion(resultSet.getString("direccion"));
                funcionario.setTelefono(resultSet.getString("telefono"));
                funcionario.setFechaNacimiento(resultSet.getDate("fecha_nacimiento"));

                // Agregar el funcionario a la lista
                funcionarios.add(funcionario);
            }

            return funcionarios;

        } finally {
            if (connection != null) {
                connection.close();
            }

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (resultSet != null) {
                resultSet.close();
            }
        }
    }

    public void crear(Funcionario funcionario) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionUtil.getConnection();

            // Obtener el ID del tipo de documento
            int tipoDocumentoId = TipoDocumentoDao.obtenerTipoDocumentoId(funcionario.getTipoDocumento());

            // Obtener el ID del estado civil
            int estadoCivilId = EstadoCivilDao.obtenerEstadoCivilId(funcionario.getEstadoCivil());
            

            // Preparar la consulta para la inserción
            preparedStatement = connection.prepareStatement(CREATE_FUNCIONARIO);
            preparedStatement.setInt(1, tipoDocumentoId);
            preparedStatement.setString(2, funcionario.getNumeroIdentificacion());
            preparedStatement.setString(3, funcionario.getNombres());
            preparedStatement.setString(4, funcionario.getApellidos());
            preparedStatement.setInt(5, estadoCivilId);
            preparedStatement.setString(6, funcionario.getSexo());
            preparedStatement.setString(7, funcionario.getDireccion());
            preparedStatement.setString(8, funcionario.getTelefono());
            preparedStatement.setDate(9, new java.sql.Date(funcionario.getFechaNacimiento().getTime()));

            preparedStatement.executeUpdate();

            System.out.println("Funcionario creado exitosamente.");
        } finally {
            // Cerrar recursos
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public Funcionario obtenerFuncionarioId(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Funcionario funcionario = null;

        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(GET_FUNCIONARIO_BY_ID);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                funcionario = new Funcionario();
                funcionario.setFuncionarioId(resultSet.getInt("funcionario_id"));
                funcionario.setTipoDocumento(resultSet.getString("tipo_documento"));
                funcionario.setNumeroIdentificacion(resultSet.getString("numero_identificacion"));
                funcionario.setNombres(resultSet.getString("nombres"));
                funcionario.setApellidos(resultSet.getString("apellidos"));
                funcionario.setEstadoCivil(resultSet.getString("estado_civil"));
                funcionario.setSexo(resultSet.getString("sexo"));
                funcionario.setDireccion(resultSet.getString("direccion"));
                funcionario.setTelefono(resultSet.getString("telefono"));
                funcionario.setFechaNacimiento(resultSet.getDate("fecha_nacimiento"));
            }
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        }

        return funcionario;
    }

    public void actualizarFuncionario(int id, Funcionario funcionario) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionUtil.getConnection();

            // Obtener el ID del tipo de documento
            int tipoDocumentoId = TipoDocumentoDao.obtenerTipoDocumentoId(funcionario.getTipoDocumento());

            // Obtener el ID del estado civil
            int estadoCivilId = EstadoCivilDao.obtenerEstadoCivilId(funcionario.getEstadoCivil());

            preparedStatement = connection.prepareStatement(UPDATE_FUNCIONARIO);

            preparedStatement.setInt(1, tipoDocumentoId);
            preparedStatement.setString(2, funcionario.getNumeroIdentificacion());
            preparedStatement.setString(3, funcionario.getNombres());
            preparedStatement.setString(4, funcionario.getApellidos());
            preparedStatement.setInt(5, estadoCivilId);
            preparedStatement.setString(6, funcionario.getSexo());
            preparedStatement.setString(7, funcionario.getDireccion());
            preparedStatement.setString(8, funcionario.getTelefono());
            preparedStatement.setDate(9, new java.sql.Date(funcionario.getFechaNacimiento().getTime()));
            preparedStatement.setInt(10, id);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Funcionario actualizado correctamente.");
            } else {
                System.out.println("No se encontró ningún funcionario con el ID proporcionado.");
            }
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    public void eliminarFuncionario(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_FUNCIONARIO);
            preparedStatement.setInt(1, id);

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Funcionario eliminado correctamente.");
            } else {
                System.out.println("No se encontró ningún funcionario con el ID proporcionado.");
            }
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    /*public void crear(Funcionario funcionario, int tipoDocumentoId, int estadoCivilId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

   
}
