package com.iudigital.employee.center.controller;

import com.iudigital.employee.center.dao.EstadoCivilDao;
import com.iudigital.employee.center.domain.EstadoCivil;
import java.sql.SQLException;
import java.util.List;

public class EstadoCivilController {

    public List<EstadoCivil> obtenerEstadosCiviles() throws SQLException {
        EstadoCivilDao estadoCivilDao = new EstadoCivilDao();
        return estadoCivilDao.obtenerEstadosCiviles();
    }
}

