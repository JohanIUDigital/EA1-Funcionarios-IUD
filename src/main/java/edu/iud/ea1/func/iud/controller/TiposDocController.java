
package edu.iud.ea1.func.iud.controller;

import edu.iud.ea1.func.iud.dao.TipoDocDao;
import edu.iud.ea1.func.iud.domain.TipoDocumento;
import java.sql.SQLException;
import java.util.List;

public class TiposDocController {
    private TipoDocDao tipoDocDao;
    
    public TiposDocController() {
        tipoDocDao = new TipoDocDao();
    }
    
    public List<TipoDocumento> contGetTipoDoc() throws SQLException{
        return tipoDocDao.obtenerTiposDoc();
    }
    
}
