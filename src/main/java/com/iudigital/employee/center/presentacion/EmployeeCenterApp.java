/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iudigital.employee.center.presentacion;

import com.iudigital.employee.center.controller.FuncionarioController;
import com.iudigital.employee.center.domain.Funcionario;
import com.iudigital.employee.center.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public class EmployeeCenterApp {

    public static void obtenerFuncionarios(FuncionarioController funcionarioController) {
        try {
            List<Funcionario> funcionarios = funcionarioController.obtenerFuncionario();
            if (funcionarios.isEmpty()) {
                System.out.println("No hay Funcionarios");
            } else {
                funcionarios.forEach(funcionario -> {
                    System.out.println("Id: " + funcionario.getFuncionarioId());
                    System.out.println("Tipo de identificación: " + funcionario.getTipoDocumento());
                    System.out.println("Número de identificación: " + funcionario.getNumeroIdentificacion());
                    System.out.println("Nombres: " + funcionario.getNombres());
                    System.out.println("Apellidos: " + funcionario.getApellidos());
                    System.out.println("Estado civil: " + funcionario.getEstadoCivil());
                    System.out.println("Sexo: " + funcionario.getSexo());
                    System.out.println("Dirección: " + funcionario.getDireccion());
                    System.out.println("Teléfono: " + funcionario.getTelefono());
                    System.out.println("Fecha de nacimiento: " + funcionario.getFechaNacimiento());
                    System.out.println("------------------------------------------------------------------------");

                });

            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        }

    }

    public static void main(String[] args) {
        FuncionarioController funcionarioController = new FuncionarioController();
        obtenerFuncionarios(funcionarioController);

    }
}
