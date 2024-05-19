
package edu.iud.ea1.func.iud.dao;

import edu.iud.ea1.func.iud.domain.Genero;
import edu.iud.ea1.func.iud.utils.ConectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class GeneroDao {
    
    private static final String GET_GENEROS = "SELECT * FROM generos";
    
    public List<Genero> obtenerGenero() throws SQLException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Genero> generos = new ArrayList<>();
        
        try {
            connection = ConectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(GET_GENEROS);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Genero genero = new Genero();
                genero.setId_genero(resultSet.getString("id_genero"));
                genero.setTxt_genero(resultSet.getString("txt_genero"));
                generos.add(genero);
            }
            return generos;
        } finally {
            if (connection != null) {
                connection.close();
            }

            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
        
    }
    
}
