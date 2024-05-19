
package edu.iud.ea1.func.iud.controller;

import edu.iud.ea1.func.iud.dao.FuncionarioDao;
import edu.iud.ea1.func.iud.domain.Funcionario;
import java.sql.SQLException;
import java.util.List;

public class FuncionarioController {
    
    //lógica pesada/de negocio de la aplicación
    private final FuncionarioDao funcionarioDao;
    
    public FuncionarioController() {
        funcionarioDao = new FuncionarioDao();
    }
    
    public List<Funcionario> contGetFuncionario() throws SQLException{
        return funcionarioDao.obtenerFuncionarios();
    }
    
    public List<Funcionario> contGetFuncionario(String filNombre, String filNumDoc) throws SQLException{
        return funcionarioDao.obtenerFuncionarios(filNombre, filNumDoc);
    }
    
    public void contCreateFuncionario(Funcionario funcionario) throws SQLException{
       funcionarioDao.crearFuncionario(funcionario);
    }
    
    public void contUpdateFuncionario(int idEmp, Funcionario funcionario) throws SQLException{
       funcionarioDao.actualizarFuncionario(idEmp, funcionario);
    }
    
    public void contDeleteFuncionario(int idEmp) throws SQLException{
       funcionarioDao.eliminarFuncionario(idEmp);
    }
}
