package com.iudigital.employee.center.controller;

import com.iudigital.employee.center.dao.FuncionarioDao;
import com.iudigital.employee.center.domain.EstadoCivil;
import com.iudigital.employee.center.domain.Funcionario;
import com.iudigital.employee.center.domain.TipoDocumento;
import java.sql.SQLException;
import java.util.List;

public class FuncionarioController {

    private FuncionarioDao funcionarioDao;

    public FuncionarioController() {
        funcionarioDao = new FuncionarioDao();
    }

    public List<Funcionario> obtenerFuncionario() throws SQLException {
        return funcionarioDao.obtenerFuncionarios();

    }

    public void crearFuncionario(Funcionario funcionario) throws SQLException {
        funcionarioDao.crear(funcionario);
    }
    

    public Funcionario obtenerFuncionarioPorId(int id) throws SQLException {
        return funcionarioDao.obtenerFuncionarioId(id);
    }

    public void actualizarFuncionario(int id, Funcionario funcionario) throws SQLException {
        funcionarioDao.actualizarFuncionario(id, funcionario);
    }

    public void eliminarFuncionario(int id) throws SQLException {
        funcionarioDao.eliminarFuncionario(id);
    }

    // Agregar métodos para obtener tipos de documento y estados civiles
    public List<TipoDocumento> obtenerTiposIdentificacion() throws SQLException {
        TipoDocumentoController tipoDocumentoController = new TipoDocumentoController();
        return tipoDocumentoController.obtenerTiposDocumento();
    }

    public List<EstadoCivil> obtenerEstadosCiviles() throws SQLException {
        EstadoCivilController estadoCivilController = new EstadoCivilController();
        return estadoCivilController.obtenerEstadosCiviles();
    }

}
