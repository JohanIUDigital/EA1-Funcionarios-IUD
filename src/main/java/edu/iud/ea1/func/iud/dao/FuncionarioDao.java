
package edu.iud.ea1.func.iud.dao;

import edu.iud.ea1.func.iud.domain.Funcionario;
import edu.iud.ea1.func.iud.utils.ConectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class FuncionarioDao {

    //lógica de acceso a datos
    private static final String GET_FUNC = "SELECT * FROM empleados AS e INNER JOIN estados_civiles ec ON e.id_est_civil = ec.id_est_civil INNER JOIN generos g ON e.id_genero = g.id_genero";
    private static final String CREATE_FUNC =  "INSERT INTO public.empleados ( nom_empl, ape_empl, id_est_civil, id_genero, dir_emp, tel_emp, fec_nac_empl, tipo_id, num_id) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String UPDATE_FUNC = "UPDATE public.empleados SET nom_empl=?, ape_empl=?, id_est_civil=?, id_genero=?, dir_emp=?, tel_emp=? WHERE id_empl= ?";
    private static final String DELETE_FUNC = "DELETE FROM public.empleados WHERE id_empl= ?";   
   
        
    public void eliminarFuncionario(int id_Emp) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
            connection = ConectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_FUNC);
            preparedStatement.setInt(1, id_Emp);
            preparedStatement.executeUpdate();
        } finally {
            if (connection != null) {
                connection.close();
            }

            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    
    }
    
    public void actualizarFuncionario(int idFun, Funcionario funcionario) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_FUNC);
            preparedStatement.setString(1, funcionario.getNombre());
            preparedStatement.setString(2, funcionario.getApellido());
            preparedStatement.setString(3, funcionario.getIdEstadoCivil());
            preparedStatement.setString(4, funcionario.getIdGenero());
            preparedStatement.setString(5, funcionario.getDireccion());
            preparedStatement.setString(6, funcionario.getTelefono());
            preparedStatement.setInt(7, idFun);
            System.out.println(preparedStatement.toString());
            preparedStatement.executeUpdate();
        } finally {
            if (connection != null) {
                connection.close();
            }

            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }    
    
    public void crearFuncionario(Funcionario funcionario) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(CREATE_FUNC);
            preparedStatement.setString(1, funcionario.getNombre());
            preparedStatement.setString(2, funcionario.getApellido());
            preparedStatement.setString(3, funcionario.getIdEstadoCivil());
            preparedStatement.setString(4, funcionario.getIdGenero());
            preparedStatement.setString(5, funcionario.getDireccion());
            preparedStatement.setString(6, funcionario.getTelefono());
            preparedStatement.setString(7, funcionario.getFechaNacimiento().toString());
            preparedStatement.setString(8, funcionario.getTipoDocumento());
            preparedStatement.setString(9, funcionario.getNumDocumento());
            System.out.println(preparedStatement.toString());
            preparedStatement.executeUpdate();
        } finally {
            if (connection != null) {
                connection.close();
            }

            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }
    
    public List<Funcionario> obtenerFuncionarios(String filNombre, String filNumDoc) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Funcionario> funcionarios = new ArrayList<>();
            System.out.println("si llego esta vaina");

        try {
            connection = ConectionUtil.getConnection();
            String GET_FUNC_FILTER = "SELECT  * FROM empleados AS e INNER JOIN estados_civiles AS ec ON e.id_est_civil = ec.id_est_civil INNER JOIN generos AS g ON e.id_genero = g.id_genero WHERE e.nom_empl LIKE '%"+ filNombre + "%' AND e.num_id LIKE '%" + filNumDoc + "%';";
            preparedStatement = connection.prepareStatement(GET_FUNC_FILTER);
            System.out.println("llego aca");
            System.out.println(preparedStatement.toString());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Funcionario funcionario = new Funcionario();
                
                funcionario.setId(resultSet.getInt("id_empl"));
                funcionario.setTipoDocumento(resultSet.getString("tipo_id"));
                funcionario.setNumDocumento(resultSet.getString("num_id"));
                funcionario.setNombre(resultSet.getString("nom_empl"));
                funcionario.setApellido(resultSet.getString("ape_empl"));
                funcionario.setIdEstadoCivil(resultSet.getString("id_est_civil"));
                funcionario.setEstadoCivil(resultSet.getString("txt_est_civil"));
                funcionario.setIdGenero(resultSet.getString("id_genero"));
                funcionario.setGenero(resultSet.getString("txt_genero"));
                funcionario.setDireccion(resultSet.getString("dir_emp"));
                funcionario.setTelefono(resultSet.getString("tel_emp"));
                String fechaSQL = formatSQLDate(resultSet.getString("fec_nac_empl"));
                funcionario.setFechaNacimiento(formatterDate(fechaSQL));
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
        }
    }   
    
    public List<Funcionario> obtenerFuncionarios() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Funcionario> funcionarios = new ArrayList<>();

        try {
            connection = ConectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(GET_FUNC);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Funcionario funcionario = new Funcionario();
                
                funcionario.setId(resultSet.getInt("id_empl"));
                funcionario.setTipoDocumento(resultSet.getString("tipo_id"));
                funcionario.setNumDocumento(resultSet.getString("num_id"));
                funcionario.setNombre(resultSet.getString("nom_empl"));
                funcionario.setApellido(resultSet.getString("ape_empl"));
                funcionario.setIdEstadoCivil(resultSet.getString("id_est_civil"));
                funcionario.setEstadoCivil(resultSet.getString("txt_est_civil"));
                funcionario.setIdGenero(resultSet.getString("id_genero"));
                funcionario.setGenero(resultSet.getString("txt_genero"));
                funcionario.setDireccion(resultSet.getString("dir_emp"));
                funcionario.setTelefono(resultSet.getString("tel_emp"));
                String fechaSQL = formatSQLDate(resultSet.getString("fec_nac_empl"));
                funcionario.setFechaNacimiento(formatterDate(fechaSQL));
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
        }
    }   
    
     public String formatSQLDate(String fechaSQL) {
        SimpleDateFormat formatoOriginal = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
        SimpleDateFormat formatoDeseado = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date fecha = formatoOriginal.parse(fechaSQL);
            String fechaFormateada = formatoDeseado.format(fecha);
            return fechaFormateada;
        } catch (Exception e) {
            return null;
        }
    }
    
    public Date formatterDate(String sFecha) {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dFecha = formato.parse(sFecha);
            return dFecha;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
}
