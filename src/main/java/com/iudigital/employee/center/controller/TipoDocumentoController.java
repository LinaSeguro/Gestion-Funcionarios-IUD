package com.iudigital.employee.center.controller;

import com.iudigital.employee.center.dao.TipoDocumentoDao;
import com.iudigital.employee.center.domain.TipoDocumento;
import java.sql.SQLException;
import java.util.List;

public class TipoDocumentoController {

    public List<TipoDocumento> obtenerTiposDocumento() throws SQLException {
        TipoDocumentoDao tipoDocumentoDao = new TipoDocumentoDao();
        return tipoDocumentoDao.obtenerTiposDocumento();
    }
}

