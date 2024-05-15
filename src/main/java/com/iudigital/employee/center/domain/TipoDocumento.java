
package com.iudigital.employee.center.domain;

import java.util.Objects;

public class TipoDocumento {
   
    
    private int tipoDocumentoId;
    private String codigo;
    private String nombre;

    // Constructor, getters y setters

    public int getTipoDocumentoId() {
        return tipoDocumentoId;
    }

    public void setTipoDocumentoId(int tipoDocumentoId) {
        this.tipoDocumentoId = tipoDocumentoId;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Sobrescribe el método toString() para devolver una representación de cadena adecuada
    @Override
    public String toString() {
        // Devuelve la descripción del tipo de documento
        return codigo;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TipoDocumento other = (TipoDocumento) obj;
        return tipoDocumentoId == other.tipoDocumentoId && Objects.equals(codigo, other.codigo) && Objects.equals(nombre, other.nombre);
    }

}
