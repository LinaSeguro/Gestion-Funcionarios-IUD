
package com.iudigital.employee.center.domain;

import java.util.Objects;


public class EstadoCivil {
    private int estadoCivilId;
    private String nombre;

    public int getEstadoCivilId() {
        return estadoCivilId;
    }

    public void setEstadoCivilId(int estadoCivilId) {
        this.estadoCivilId = estadoCivilId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    @Override
    public String toString() {
        return nombre; 
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        EstadoCivil other = (EstadoCivil) obj;
        return estadoCivilId == other.estadoCivilId && Objects.equals(nombre, other.nombre);
    }
          
}
