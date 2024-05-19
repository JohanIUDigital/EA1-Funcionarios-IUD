
package edu.iud.ea1.func.iud.dao;

import edu.iud.ea1.func.iud.domain.EstadoCivil;
import edu.iud.ea1.func.iud.utils.ConectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstadoCivilDao {
    
    private static final String GET_EST_CIVILES = "SELECT * FROM estados_civiles";
    
    public List<EstadoCivil> obtenerEstCivil() throws SQLException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<EstadoCivil> estCiviles = new ArrayList<>();
        
        try {
            connection = ConectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(GET_EST_CIVILES);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                EstadoCivil estCivil = new EstadoCivil();
                estCivil.setId_est_civil(resultSet.getString("id_est_civil"));
                estCivil.setTxt_est_civil(resultSet.getString("txt_est_civil"));
                estCiviles.add(estCivil);
            }
            return estCiviles;
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
