
package edu.iud.ea1.func.iud.dao;

import edu.iud.ea1.func.iud.domain.TipoDocumento;
import edu.iud.ea1.func.iud.utils.ConectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class TipoDocDao {
    
    private static final String GET_TIPOS_DOC = "SELECT * FROM tipo_doc";
    
    public List<TipoDocumento> obtenerTiposDoc() throws SQLException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<TipoDocumento> tiposDoc = new ArrayList<>();
        
        try {
            connection = ConectionUtil.getConnection();
            preparedStatement = connection.prepareStatement(GET_TIPOS_DOC);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TipoDocumento tipoDoc = new TipoDocumento();
                tipoDoc.setId_tipo_doc(resultSet.getString("tipo_id"));
                tipoDoc.setTxt_tipo_doc(resultSet.getString("txt_tipo_id"));
                tiposDoc.add(tipoDoc);
            }
            return tiposDoc;
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
