package edu.iud.ea1.func.iud.view;

import edu.iud.ea1.func.iud.controller.FuncionarioController;
import edu.iud.ea1.func.iud.domain.Funcionario;
import java.sql.SQLException;
import java.util.List;

public class EA1FuncionariosIUDigital {

    public static void mostrarEmpleados(FuncionarioController funcionarioController) {

        try {
            List<Funcionario> funcionarios = funcionarioController.contGetFuncionario();

            if (!funcionarios.isEmpty()) {
                funcionarios.forEach(funcionario -> {
                    System.out.println("Id: " + funcionario.getId());
                    System.out.println("Nombre: " + funcionario.getNombre());
                    System.out.println("Apellido: " + funcionario.getApellido());
                    System.out.println("Dirección: " + funcionario.getDireccion());
                    System.out.println("Estado Civil: " + funcionario.getEstadoCivil());
                    System.out.println("Teléfono: " + funcionario.getTelefono());
                    System.out.println("______________________________________");
                });
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
        FuncionarioController funcionarioController = new FuncionarioController();
        mostrarEmpleados(funcionarioController);

    }
}
